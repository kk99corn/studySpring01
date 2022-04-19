package hello.core.member;

public class MemberServiceImpl implements MemberService{

	private final MemberRepository m = new MemoryMemberRepository();	// OCP위반

	@Override
	public void join(Member member) {
		m.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return m.findById(memberId);
	}
}
