import java.util.ArrayList;
import java.util.List;

public class StringConcatenation {

    List<String> countryData = new ArrayList<String>();

    public StringConcatenation() {
        countryData.add("India");
        countryData.add("Australia");
        countryData.add("USA");
        countryData.add("UK");
    }

    public String printDataString() {
        String countries = "";
        for (String country : countryData) {
            countries = countries + ", " + country;
        }
        return countries.substring(1);
    }

    public String printDataStringBuffer() {
        StringBuffer stringBuffer = new StringBuffer();
        for (String country : countryData) {
            stringBuffer.append(country).append(",");
        }
        String countryData = stringBuffer.toString();
        countryData = countryData.substring(0, countryData.length()-1);
        return countryData;
    }

    public String printDataStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String country : countryData) {
            stringBuilder.append(country).append(",");
        }
        String countryDate = stringBuilder.toString();
        countryDate = countryDate.substring(0, countryDate.length() - 1);
        return countryDate;
    }

    public static void main(String[] args) {
        StringConcatenation stringConcatenation = new StringConcatenation();
        String countries = stringConcatenation.printDataStringBuffer();
        System.out.println(countries);
    }
}
