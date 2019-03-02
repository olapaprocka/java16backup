package pl.mpas.advances_programming.null_handling;

import java.util.Optional;

public class Example {

    public static void main(String[] args) {

        Computer fullOne = new Computer(new GraphicsCard(new Chipset("nVidia")));
        Computer withOutVendor = new Computer(new GraphicsCard(new Chipset(null)));
        Computer withOutChipset = new Computer(new GraphicsCard(null));
        Computer withOutGraphicsCard = new Computer(null);
        Computer mebyComputer = null;

        Optional<String> maybeMyName = Optional.ofNullable(null);

        if (maybeMyName.isPresent()){
            System.out.println(maybeMyName.get());
        }

        maybeMyName.ifPresent(s -> System.out.println(s));
        maybeMyName.ifPresent(System.out::println);
    }


    public static String getVendorNameNaiveImpl(Computer computer){
       return computer.getGraphicsCard().getChipset().getVendorName();
    }

    public  static String getVendorNameImpl (Computer computer){
        String result = "unknown";

if (null != computer){
    GraphicsCard mabyCard = computer.getGraphicsCard();
    if(null!=mabyCard){
        Chipset maybeChipset = mabyCard.getChipset();
        if(null != maybeChipset) {
            String maybeVendorName = maybeChipset.getVendorName();
            if (null!=maybeVendorName){
                result = maybeVendorName;
            }
        }
    }
}
return result;
    }
    public static Optional<String> getVendorNameJava8Way(Computer computer){
       return Optional.ofNullable(computer)
                .map(computer1 -> computer1.getGraphicsCard())
                .map(graphicsCard -> graphicsCard.getChipset())
                .map(chipset -> chipset.getVendorName());
                //.orElseGet(() -> "unknown");

    }

    public static Optional<String> getVendorNameJava8WayS(Computer computer){
        return Optional.ofNullable(computer)
                .map(Computer :: getGraphicsCard)
                .map(GraphicsCard :: getChipset)
                .map(Chipset :: getVendorName);
        //.orElseGet(() -> "unknown");

    }

}
