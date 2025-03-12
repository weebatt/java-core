package enums;

public enum LittleMenStatus {
    CLOSEYES("Отключился"),
    WHITEFACE("Побледнел"),
    WEAKPULSE("Слабый пульс"),
    NOHEARTBEAT("Нет сердцебиения"),
    DEATH("Вмер Ж(, нашатырь не помог =("),
    WAKEUP("Очнулся!!!!!"),
    NORMAL("Наелся и чувствует себя превосходно"),
    HUNGRY("Ничего не съел :("),
    WATCHING(" наблюдают за работой "),
    NOSTATUS("");

    private final String s;

    LittleMenStatus(String s){
        this.s = s;
    }

    public String getStringStatus(){
        return s;
    }
}

