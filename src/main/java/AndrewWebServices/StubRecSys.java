package AndrewWebServices;

public class StubRecSys implements RecSys {
    @Override
    public String getRecommendation(String accountName) {
        return "StubMovie";
    }
}
