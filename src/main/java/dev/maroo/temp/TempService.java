package dev.maroo.temp;

import dev.maroo.temp.repo.TempRepository;
import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 *
 * @author Maroo
 */
public class TempService {
	
	private final TempRepository repository;

	public TempService(TempRepository repository) {
		this.repository = repository;
	}

	public Action<Chain> tempApi() {
		return apiChain -> apiChain.prefix("temp", define());
	}

	private Action<Chain> define() {
		return chain -> chain.get(ctx -> ctx.render(""));

	}
	
//	private Handler listTemp() {
//		return ctx -> ctx.render(JSONMapping.toJSONPromise( ));
////        return () -> renderSecure(ctx, (any) -> repository.listSensors());
//    }

}
