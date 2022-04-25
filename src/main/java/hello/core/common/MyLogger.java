package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// request 스코프 지정
// 해당 빈은 HTTP 요청 당 하나씩 생성되고, HHTP 요청이 끝나는 시점에 소멸된다.
public class MyLogger {
	private String uuid;
	private String requestURL;

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public void log(String message) {
		System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
	}

	@PostConstruct
	public void init() {
		uuid = UUID.randomUUID().toString();
		System.out.println("[" + uuid + "] request scope bean create: "  + this);
	}

	@PreDestroy
	public void destroy() {
		System.out.println("[" + uuid + "] request scope bean close: "  + this);
	}
}
