package com.zcl.study.schedule;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * spring-demo .
 *
 * @description: 考试时间为120分钟，30分钟后才可交卷，当时间到了，或学生都交完卷了者考试结束。.
 * @author: Chenglin Zhu .
 * @date: 20-4-17 .
 */
class Student implements Runnable, Delayed {
	private String name;
	private long submitTime; //交卷时间
	private long workTime; //考试时间

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String name, long submitTime) {
		super();
		this.name = name;
		workTime = submitTime;
		//都转为转为ns
		this.submitTime = TimeUnit.NANOSECONDS.convert(submitTime, TimeUnit.MILLISECONDS) + System.nanoTime();
	}

	@Override
	public void run() {
		System.out.println(name + " 交卷,用时" + workTime / 100 + "分钟");
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(submitTime - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		Student student = (Student) o;
		return Long.compare(submitTime, student.submitTime);
	}

	public static class EndExam extends Student {
		private ExecutorService exec;
		public EndExam(int submitTime, ExecutorService exec) {
			super(null, submitTime);
			this.exec = exec;
		}

		@Override
		public void run() {
			exec.shutdownNow();
		}
	}

}

class Teacher implements Runnable {
	private DelayQueue<Student> students;
	private ExecutorService exec;

	public Teacher(DelayQueue<Student> students, ExecutorService exec) {
		super();
		this.students = students;
		this.exec = exec;
	}


	@Override
	public void run() {
		try {
			System.out.println("考试开始……");
			while (!Thread.interrupted()) {
				students.take().run();
			}
			System.out.println("考试结束……");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

public class Exam {
	static final int STUDENT_SIZE = 45;
	public static void main(String[] args) {
		Random r = new Random();
		int minutes = 12000;
		int delayMinutes = 3000;
		int restMinutes = minutes - delayMinutes;
		DelayQueue<Student> students = new DelayQueue<>();
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < STUDENT_SIZE; i++) {
			students.put(new Student("学生" + (i + 1), delayMinutes + r.nextInt(restMinutes)));
		}
		students.put(new Student.EndExam(minutes, executorService)); //1200为考试结束时间
		executorService.execute(new Teacher(students, executorService));
	}
}
