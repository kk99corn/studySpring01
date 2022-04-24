package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;    // 인터페이스만 의존하도록 수정

	// @Autowired	// 생성자가 1개면 생략가능
	public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}
	// @RequiredArgsConstructor << 롬복 어노테이션이 final 변수 기반으로 생성자 만들어줌

	@Override
	public Order createOrder(Long memberId, String itemName, int price) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, price);

		return new Order(memberId, itemName, price, discountPrice);
	}

	// 테스트용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

}
