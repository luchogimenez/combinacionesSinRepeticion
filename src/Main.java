import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Map<String, List<String>> channel = Collections
                //.singletonMap("channel", Arrays.asList("onlinebanking"));
        Map<String, List<String>> functionality = Collections
                .singletonMap("functionality", Arrays.asList("alias_administrativo","cobranza","pago_proveedores","cobro_clientes"));
        //Map<String, List<String>> currency = Collections
                //.singletonMap("currency", Arrays.asList("ars","usd"));

        //Set<Map<String,List<String>>> domains = new HashSet<>(Arrays.asList(channel,functionality,currency));
        Set<Map<String,List<String>>> domains = new HashSet<>(Arrays.asList(functionality));
        System.out.println("Hello world!");
        System.out.println("dominios: "+domains);
        domains.stream().forEach(d->{
            combinationsDomains(d);
        });


    }
    public static int combinationQty(Integer qty){
        int sum = 0;
        if(qty>0){
        for (int i=1;i<=qty;i++){
            sum+=factorial(qty)/factorial(qty-i)/factorial(i);
        }
        return sum;
        }
        return 0;
    }
    public static int factorial(Integer n) {
        if (n==0)
            return 1;
        else
            return n * factorial(n-1);
    }

    public static <i> Map<String,List<String>> combinationsDomains(Map<String,List<String>> domain){


        String key = domain.keySet().toString();
        System.out.println("domain_path: "+domain.keySet());

        //System.out.println("domain_value: "+domain.entrySet());

        int valueQty = getValueQty(domain);
        for(int i=0;i<valueQty;i++){

        }
        System.out.println("cant: "+valueQty);
        List<String> values = getValuesDomain(domain);
        System.out.println("values: "+values);
        Set<String> comb = possibleCombinations(valueQty, values);
        Set<Map<String,List<String>>> setCom = new HashSet<>();
        comb.stream().forEach(x->{
            List<String> m = Arrays.asList(x.split(","));
            System.out.println(m);
            setCom.add(Collections
                    .singletonMap(key,m));
        });
        System.out.println("setCom: "+setCom);
        return domain;
    }

    private static Set<String> possibleCombinations(int valueQty, List<String> values) {
        Set<String> comb = new HashSet<>();

        for(int i=1;i<valueQty+1;i++){
            combinationWithoutRepetition(comb,values,"",i, valueQty,0);
        }
        return comb;
    }

    private static void combinationWithoutRepetition(Set<String> comb,List<String> elem, String act, int n, int r, int y) {
        if (n == 0) {
            //System.out.println("-");
            //System.out.println(act);
            comb.add(act);

        } else {
            for (int i = y; i < r; i++) {
                if (!act.contains(elem.get(i))) { // Controla que no haya repeticiones
                    combinationWithoutRepetition(comb,elem, act + elem.get(i)+",", n - 1, r,i+1);
                }
            }
        }

    }

    private static int getValueQty(Map<String, List<String>> domain) {
        List<String> list = new ArrayList<>();
        domain.entrySet().stream().forEach(x->x.getValue().stream().forEach(y->list.add(y)));
        int cant = list.size();
        return cant;
    }
    private static List<String> getValuesDomain(Map<String, List<String>> domain){

        return domain.entrySet().stream().findFirst().get().getValue();
    }

}