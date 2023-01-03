package com.blog;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.config.AppConstans;
import com.blog.entities.Role;
import com.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(this.passwordEncoder.encode("xyz"));
		
		try {
			Role role = new Role();
			role.setId(AppConstans.ADMIN_USER);
			role.setName("ADMIN_USER");
			
			Role role1 = new Role();
			role1.setId(AppConstans.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			List<Role> roles = new ArrayList<>();
			roles.add(role);
			roles.add(role1);
			
			List<Role> result = this.roleRepo.saveAll(roles);
			
			result.forEach(r->{
				System.out.println(r.getId()+": "+r.getName());
			});
			
			
		}catch (Exception e) {
			e.getStackTrace();
		}
	}

} 
