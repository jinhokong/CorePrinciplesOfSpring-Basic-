package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberSerivceImpl;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberSerivceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberSerivceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberSerivceImpl memberService = ac.getBean("memberService", MemberSerivceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberSerivceImpl.class);
    }

    @Test
    @DisplayName("없는 빈 이름으로 조회")
    void findBeanByName_fail() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("XXX", MemberService.class));
    }
}
