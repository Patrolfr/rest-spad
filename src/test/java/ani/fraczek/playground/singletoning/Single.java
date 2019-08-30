package ani.fraczek.playground.singletoning;


import lombok.Getter;

class Single {
    private static Single singleInstance;
    @Getter
    private static int getInstanceCounter;

    public static Single getInstance() {
        Single.getInstanceCounter++;
        if(singleInstance == null)
            singleInstance = new Single();
        return singleInstance;
    }

    public static int getGetInstanceCounter() {
        return getInstanceCounter;
    }
}
