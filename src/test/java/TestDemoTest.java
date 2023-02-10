import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import java.util.Random;
import java.util.stream.Stream;
import static org.mockito.Mockito.spy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDemoTest {
   private TestDemo testDemo;
	@BeforeEach
	void setUp() {
		testDemo = new TestDemo();
	}

	@ParameterizedTest 
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	public void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		}
		else 
			assertThatThrownBy(() -> { testDemo.addPositive(a, b);}).isInstanceOf(IllegalArgumentException.class);
	}
	public static Stream<Arguments> argumentsForAddPositive() {
		return Stream.of(arguments(2,4,6,false), arguments(4,6,10,false), arguments(0,10,10,true));
	} 
	@Test
	 void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}

}
