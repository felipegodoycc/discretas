matriz = [  [0,1,0,0,1,0],
            [1,0,1,0,0,0],
            [0,1,0,1,0,0],
            [0,0,1,0,1,0],
            [1,0,0,1,0,1],
            [0,0,0,0,1,0]]

def obtener_grados(m):
    grad = []
    suma = 0
    for i in range(len(m)):
        for j in range(len(m)):
            suma+=m[i][j]
        grad.append(suma)
        suma=0
    return grad

def es_grafo(g):
    g.sort(reverse=True)
    for i in range(len(g)):
        ct=g[i]
        for j in range(i+1,i+1+ct):
            g[j]=g[j]-1
            if (g[j]==(-1)):
                return False
        g.sort(reverse=True)
    return True

def imprimir_matriz(m):
    for i in range(len(m)):
        for j in range(len(m)):
            print(m[i][j], end = " ")
        print()

def multipl(m,ma):
    columnas=len(m)
    matrizR=[]
    temp=[]
    #print(m)
    #for i in range(columnas):
    #    temp.append(0)
    #print(temp)
    for k in range(columnas):
        matrizR.append([0,0,0,0,0,0])
    for i in range(columnas):
        for j in range(columnas):
            for k in range(columnas):
                matrizR[i][j]+= m[i][k] * ma[k][j]
    #imprimir_matriz(matrizR)
    return matrizR

def es_conexo(m):
    ma = m
    n = len(m)
    con = []
    for i in range(len(m)):
        con.append([0,0,0,0,0,0])
    for k in range(1,n-1):
        m = multipl(m,ma)
        for i in range(n):
            for j in range(n):
                con[i][j]+=m[i][j]
    for i in range(n):
        for j in range(n):
            if(con[i][j]==0):
                return False
    return True

def cantidad_caminos(m,k):
    ini = int(input("Ingrese numero vertice inicial: "))
    fin = int(input("Ingrese numero vertice final: "))

    n = len(m)
    ma = m

    for i in range(1,k):
        ma = multipl(ma,m)

    for i in range(n):
        for j in range(n):
            if((i==(ini-1)) and (j==(fin-1))):
                print("Cantidad de caminos de grado "+ k+ " desde "+ini+" hasta "+fin+" es " + ma[i][j])
