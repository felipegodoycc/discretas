package grafos;
import java.util.Scanner;

public class Grafos 
{
    public static void CantidadCaminos (int M[][], int MC[][] ,int n, int k)
    {//FUNCION PARA DETERMINAR LA CANTIDAD DE CAMINOS QUE HAY DESDE UN VERTICE A OTRO
        Scanner sc = new Scanner(System.in);
        int i, j, exp, i0, j0;
        //Ingreso de los vertices
        System.out.print("  Ingrese el numero del vertice inicial: ");
        i0 = sc.nextInt();
        System.out.print("  Ingrese el numero del vertice final: ");
        j0 = sc.nextInt();
        //Copia de la matriz original a las correspondientes
        for (i=0; i<n; i++)
        {
            for (j=0; j<n; j++)
            {
                M[i][j]=MC[i][j];
            }    
        }
        for (exp=1; exp<k; exp++)
            MultiplicacionMatriz(M, MC, n);
        System.out.print("  Matriz de caminos de orden: "+k+"\n");
        MostrarMatriz(M, n);
        System.out.print("\n");
        for (i=0; i<n; i++)
        {
            for (j=0; j<n; j++)
            {
                if ((i==(i0-1))&&(j==(j0-1)))
                {
                    System.out.print("\n  La cantidad de caminos de orden "+k+" desde el vertice "+i0+" hasta el vertice "+j0+" es: "+M[i][j]+"\n");
                }
            }
        }  
    }
    
    public static boolean MatrizConexa(int M[][], int MC[][], int n)
    {//FUNCION PARA DETERMINAR SI UNA MATRIZ ES CONEXA
        int i, j, exp;
        int [][] R;
        R=new int[100][100];
        for (i=0; i<n; i++)
        {
            for (j=0; j<n; j++)
            {
                R[i][j]=MC[i][j];//Copia de la matriz adjunta original
            }
        }
        for (exp=1; exp<n-1; exp++)
        {
            MultiplicacionMatriz(M, MC, n);//Funcion para ir elevando a "n" la matriz original
            for (i=0; i<n; i++)
            {
                for (j=0; j<n; j++)
                {
                    R[i][j]=R[i][j]+M[i][j];//Acumulacion de sumas de cada posicion
                }
            }
        }
        boolean op=true;
        for (i=0; i<n; i++)
        {
            for (j=0; j<n; j++)
            {
                if (R[i][j]==0)
                {//Verificacion de que no halla elementos nulos en la matriz
                    op=false;
                }
            }
        }
        return op;
    }
    
    public static void Burbuja(int V[], int n)
    {//METODO DE LA BURBUJA PARA ORDENAR VECTORES
        int i, j, temp;
        for (i=0; i<n; i++)
        {
            for (j=0; j<n-1; j++)
            {
                if (V[j]<V[j+1])
                {
                    temp=V[j];
                    V[j]=V[j+1];
                    V[j+1]=temp;
                }
            }
        }
    }
    
    public static boolean Grafo(int FV[], int n)
    {//FUNCION PARA VERIFICAR SI UN GRAFO ES GRAFICABLE A PARTIR DE SUS FORMULAS DE VALENCIAS
        boolean op=true;
        int i, j, k, temp;
        for (i=0; i<n; i++)
        {
            temp=FV[i];
            k=i+1;
            for (j=0; j<temp; j++)
            {//RESTA EN 1 DE CADA ELEMENTO
                FV[k]=FV[k]-1;
                k++;
            }
            Burbuja(FV, n);//ORDENAMIENTO DE MAYOR A MENOR
        }
        for (i=0; i<n; i++)
            if (FV[i]<0)//VERIFICACION DE NUMEROS NEGATIVOS
                return false;
        return true;
    }
    
    public static void LLenadoMatriz(int M[][], int n)
    {//PROCEDIMIENTO PARA EL LLENADO DE LA MATRIZ
        Scanner sc = new Scanner(System.in);
        int i, j;
        System.out.print("  LLENADO DE LA MATRIZ\n");
        for (i=0; i<n; i++)
        {
            for (j=0; j<n; j++)
            {
                System.out.print(" Dato ["+(i+1)+"]["+(j+1)+"]: ");
                M[i][j]=sc.nextInt();
            }
            System.out.println("\n");
        }
    }
    
    public static void FormulaDeValencia(int M[][], int n, int V[])
    {//PROCEDIMIENTO PARA OBTENER LA FORMULA DE VALENCIA
        int i, j, sum=0;
        for (i=0; i<n; i++)
        {
            for (j=0; j<n; j++)
            {
                sum=sum+M[i][j];//Suma para determinar el grado del vertice
            }
            V[i]=sum;//Copia del grado del vertice en un Vector
            sum=0;
        }
    }
    
    public static void MultiplicacionMatriz(int M[][], int MC[][], int n)
    {//ALGORITMO PARA MULTIPLICAR MATRICES
        int i, j, k;
        int [][] C;
        C = new int [100][100];
        for(i=0; i<n; i++)
            for (j=0; j<n; j++)
                C[i][j]=0;
        for (i=0; i<n; i++)
        {
            for (j=0; j<n; j++)
            {
                for (k=0; k<n; k++)
                {
                    C[i][j]= C[i][j] + (M[i][k]*MC[k][j]);
                }
            }
        }
        for (i=0; i<n; i++)
        {
            for (j=0; j<n; j++)
            {
                M[i][j]=C[i][j];
            }
        }
    }
    
    public static void MostrarMatriz(int M[][], int n)
    {
        int i, j;
        for (i=0; i<n; i++)
        {
            for (j=0; j<n; j++)
            {
                if (j==0)
                    System.out.print("["+M[i][j]+" ");
                else if (j==(n-1))
                    System.out.print(M[i][j]+"]");
                else if (j>1 || j<(n-1))
                    System.out.print(M[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int n, i, j, caminos;
        int [] V;
        int [][] M, MC;
        V = new int[100];
        M = new int [100][100];
        MC = new int [100][100];
        System.out.print("  Ingrese la dimension de la matriz de adyacencia: ");
        n = sc.nextInt();
        LLenadoMatriz(M, n);
        System.out.print("  Matriz ingresada: \n");
        MostrarMatriz(M, n);
        //Copia de la matriz ingresada a una auxiliar
        for (i=0; i<n; i++)
        {
            for (j=0; j<n; j++)
            {
                MC[i][j]=M[i][j];
            }
        }
        //Extraccion de las valencias de cada vertice
        FormulaDeValencia(M, n, V);
        //Ordenamiento descendente de las valencias obtenidas
        Burbuja(V, n);
        System.out.print("\n");
        if (Grafo(V, n)==false)
        {
            System.out.print("  Grafo no es graficable\n");
        }
        else
        {
            System.out.print("  Grafo graficable\n");
            if (MatrizConexa(M, MC, n)==true)
            {
                System.out.print("  Es un Grafo conexo\n");
            }
            else 
            {
                System.out.print("  No es un Grafo conexo\n");
            }
            
            System.out.print("  Ingrese el orden de caminos que desea saber: ");
            caminos = sc.nextInt();
            CantidadCaminos(M, MC, n, caminos);
        }
    }   
}
