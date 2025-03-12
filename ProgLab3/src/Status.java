public enum Status {
    CLOSEYES("Отключился"),
    WHITEFACE("Побледнел"),
    WEAKPULSE("Слабый пульс"),
    NOHEARTBEAT("Нет сердцебиения"),
    DEATH("Вмер Ж(, нашатырь не помог =("),
    WAKEUP("Очнулся!!!!!"),
    NORMAL("Наелся и чувствует себя превосходно"),
    HUNGRY("Ничего не съел :("),
    NOSTATUS("");

    private String s;

    Status(String s){
        this.s = s;
    }

    public String getStringStatus(){
        return s;
    }
}

