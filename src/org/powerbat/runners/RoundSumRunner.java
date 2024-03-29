package org.powerbat.runners;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

import org.powerbat.executor.Result;
import org.powerbat.interfaces.Manifest;
import org.powerbat.interfaces.Runner;

@Manifest(category = "Logic", instructions = "Round each number to the nearest number divisible by 10.\nThen, return the sum of all the rounded numbers.\n5 rounds to 10\n14 rounds to 10\n23 rounds to 20.", version = 1.0D, className = "RoundSum", level = 3, method = "int roundSum(int[] nums)")
public class RoundSumRunner extends Runner {

	Manifest manifest = this.getClass().getAnnotation(Manifest.class);

	@Override
	public Result[] getResults(Class<?> clazz) {
		try {
			final Method method = clazz.getMethod("roundSum", int[].class);
			final Random ran = new Random();
			final Result[] results = new Result[15];
			for (int j = 0; j < results.length; j++) {
				int childCount = ran.nextInt(5) + ran.nextInt(5);
				int[] nums = new int[childCount];
				for (int i = 0; i < nums.length; i++) {
					nums[i] = ran.nextInt(100);
				}
				results[j] = new Result(
						method.invoke(clazz.newInstance(), nums),
						roundSum(nums), Arrays.toString(nums));
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result[]{};
	}

	public int roundSum(int[] nums) {
		int sum = 0;
		for (int i : nums) {
			sum += (int) ((i / 10d) + 0.5) * 10;
		}
		return sum;
	}
}