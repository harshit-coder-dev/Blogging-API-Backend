package com.blog;

import com.blog.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationApisApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

//	@Test
//	public void repoTest(){
//		String className=userRepo.getClass().getName();
//		String packageName=userRepo.getClass().getPackageName();
//		System.out.println(className);
//		System.out.println(packageName);
//	}

}
