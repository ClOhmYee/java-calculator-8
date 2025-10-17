package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Application {
    static List<String> separator;

    public static void main(String[] args) {
        // 입력 받기
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String iStr = Console.readLine();

        // 기본 구분자를 생성
        separator = new ArrayList<>(Arrays.asList(",", ":"));

        // 커스텀 구분자를 파악해 추가하고, 시작 지점을 구하기
        int start = checkCustom(iStr);

        // 완성된 구분자 리스트를 정규식으로 변환해 숫자 구분
        String sepRegex = String.join("|", separator);
        String part = iStr.substring(start);
        String[] numbers = part.split(sepRegex);
        for (String s : numbers) {
            System.out.println(s);
        }
        
        // 숫자들의 합 계산
        int sum = 0;

        for (String n : numbers) {
            int curNum;
            try {
                curNum = Integer.parseInt(n);
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("분류 오류 : 숫자로 변환하는 과정에서 오류가 발생했습니다.", e);
            }

            if (curNum <= 0) {
                throw new IllegalArgumentException("입력 오류 : 양수가 아닌 수를 입력할 수 없습니다.");
            }

            sum += curNum;
        }

        // 결과 출력
        System.out.printf("결과 : %d\n", sum);
    }

    // 커스텀 구분자를 추출하는 함수
    public static int checkCustom(String iMsg) {
        int curIndex = 0;
        int msgLength = iMsg.length();
        
        // input msg 전체에 대해 구분자 분석 진행
        // 여러 가지의 구분자와 다양한 길이의 구분자에 대해서도 유동적으로 대처할 수 있도록 구성
        while (curIndex < msgLength) {
            if (iMsg.indexOf("//", curIndex) == curIndex) {
                int nextIndex = iMsg.indexOf("\\n", curIndex);

                if (nextIndex == -1 || nextIndex == curIndex + 2) {
                    throw new IllegalArgumentException("분류 오류 : 커스텀 분류자를 파악할 수 없습니다.");
                }

                String custom = iMsg.substring(curIndex + 2, nextIndex);
                // 특수한 경우(.(온점) 등)에 구분자 오용 방지하기 위한 코드
                separator.add(Pattern.quote(custom));

                curIndex = nextIndex + 2;
            }
            else {
                break;
            }
        }

        return curIndex;
    }
}
