package com.example.jwt.global.initData;

import com.example.jwt.domain.article.service.ArticleService;
import com.example.jwt.domain.member.entity.Member;
import com.example.jwt.domain.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
    @Bean
    CommandLineRunner initData(MemberService memberService, ArticleService articleService, PasswordEncoder passwordEncoder) {
        String password = passwordEncoder.encode("1234");
        return args -> {
            Member member1 = memberService.join("user1", password, "user1@test.com");
            Member member2 = memberService.join("user2", password, "user2@test.com");
            Member admin = memberService.join("admin", password, "amdin@test.com");

            articleService.write(admin, "제목 1", "내용 1");
            articleService.write(admin, "제목 2", "내용 2");
            articleService.write(member1, "제목 3", "내용 3");
            articleService.write(member1, "제목 4", "내용 4");
            articleService.write(member2, "제목 5", "내용 5");
            articleService.write(member2, "제목 6", "내용 6");

        };
    }
}