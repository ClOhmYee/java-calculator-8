package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // 입력 받기
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String iStr = Console.readLine();

        // 기본 구분자를 생성
        List<String> separator = new ArrayList<>(Arrays.asList(",", ":"));

        // 완성된 구분자 리스트를 정규식으로 변환해 숫자 구분
        String sepRegex = String.join("|", separator);
        String[] numbers = iStr.split(sepRegex);
    
        int sum = 0;
        
        // 숫자들의 합 계산
        for (String n : numbers) {
            try {
                sum += Integer.parseInt(n);
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("분류 오류 : 숫자로 변환하는 과정에서 오류가 발생했습니다.", e);
            }
        }

        // 결과 출력
        System.out.printf("결과 : %d\n", sum);
    }
}
