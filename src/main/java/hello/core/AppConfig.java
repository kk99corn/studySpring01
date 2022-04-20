package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/*
 * AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성
 * */
public class AppConfig {

	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}

	private MemoryMemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	public OrderService orderService() {
		return new OrderServiceImpl(
				memberRepository(),
				discountPolicy()
		);
	}

	public DiscountPolicy discountPolicy() {
		return new RateDiscountPolicy();
	}
}
