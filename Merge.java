public class Merge {

    private Merge() {}

    // Faz merge de a[lo..mid] com a[mid+1..hi] usando o array auxiliar aux[]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);       // pré-condição: metade esquerda ordenada
        assert isSorted(a, mid + 1, hi);   // pré-condição: metade direita ordenada

        // copia a[lo..hi] para aux[lo..hi]
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo;        // ponteiro da metade esquerda
        int j = mid + 1;   // ponteiro da metade direita

        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++]; // esgotou esquerda
            else if (j > hi)               a[k] = aux[i++]; // esgotou direita
            else if (less(aux[j], aux[i])) a[k] = aux[j++]; // direita menor
            else                           a[k] = aux[i++]; // esquerda menor ou igual
        }

        assert isSorted(a, lo, hi); // pós-condição: a[lo..hi] ordenado
    }

    // Ordena a[lo..hi] recursivamente (top-down)
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);        // ordena metade esquerda
        sort(a, aux, mid + 1, hi);    // ordena metade direita
        merge(a, aux, lo, mid, hi);   // intercala os dois lados
    }

    // Ponto de entrada público
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length]; // array auxiliar alocado uma única vez
        sort(a, aux, 0, a.length - 1);
        assert isSorted(a);
    }

    // -----------------------------------------------------------------------
    // Métodos auxiliares (mesma estrutura do Quick.java)
    // -----------------------------------------------------------------------

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }

    // -----------------------------------------------------------------------
    // main — mesmo padrão do Quick.java, lê Words3.txt via StdIn
    // -----------------------------------------------------------------------
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        String[] a = StdIn.readAllStrings();
        Merge.sort(a);
        show(a);

        long end = System.currentTimeMillis();
        System.out.println("Tempo: " + (end - begin) + " ms");
    }
}
