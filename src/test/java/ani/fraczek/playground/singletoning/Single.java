package ani.fraczek.playground.singletoning;


import lombok.Getter;

class Single {
    private static Single singleInstance;
    @Getter
    private static int instanceCallCounter;

    public static Single getInstance() {
        Single.instanceCallCounter++;
        if(singleInstance == null)
            singleInstance = new Single();
        return singleInstance;
    }

}
