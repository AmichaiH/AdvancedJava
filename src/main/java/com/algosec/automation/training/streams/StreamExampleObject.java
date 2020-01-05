package main.java.com.algosec.automation.training.streams;

class SteamExampleObject {
    private int firstNum, secondNum;

    SteamExampleObject(int firstNum, int secondNum) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }

    @Override
    public String toString() {
        return "First num:" + firstNum + " Second num:" + secondNum;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

}
