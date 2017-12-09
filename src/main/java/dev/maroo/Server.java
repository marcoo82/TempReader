package dev.maroo;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.maroo.json.configuration.CustomJacksonMapper;
import dev.maroo.temp.repo.TempRepository;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import javaslang.Function1;
import javaslang.control.Try;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.handling.RequestLogger;
import static ratpack.jackson.Jackson.json;
import ratpack.server.RatpackServer;
import ratpack.server.RatpackServerSpec;

/**
 *
 * @author Maroo
 */
public class Server {

	private final TempRepository tempRepository;

	private final RatpackServer ratpackServer;

	public Server(TempRepository tempRepository) {
		this.tempRepository = tempRepository;
		ratpackServer = Try.of(() -> createDefaultServer(defineApi())).onFailure(this::handleError).get();
	}

	public void start() {
		Try.run(() -> this.ratpackServer.start()).onFailure(System.out::println);

	}

	public void stop() {
		Try.run(() -> this.ratpackServer.stop());
	}

	private static RatpackServer createDefaultServer(Action<Chain> handlers) {
		return createDefaultServer(handlers, Server::configuration);
	}

	private static RatpackServer createDefaultServer(Action<Chain> handlers, Function1<RatpackServerSpec, RatpackServerSpec> configuration) {
		try {
			return RatpackServer.of(server -> configuration.apply(server)
					.registryOf(r -> r.add(ObjectMapper.class, new CustomJacksonMapper()))
					.handlers(chain -> handlers.execute(chain.all(RequestLogger.ncsa())))
			);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private Action<Chain> defineApi() {
		return apiChain -> apiChain
//				.get(":name", ctx -> ctx.render("Hello " + ctx.getPathTokens().get("name") + "!"));
				.get("sensor/:name", ctx -> ctx.render(json(tempRepository.temperature(Integer.valueOf(ctx.getPathTokens().get("name"))))))
				.get("checkError",ctx -> ctx.render(json(tempRepository.chceckError())))
//				.get(ctx -> ctx.render(json(tempRepository.temperatures())));
//				.get(ctx -> ctx.render(json(new Sensor(1, "22,2", LocalDateTime.now()))));
				.get(ctx -> ctx.render("TempMonitoring Rest API works!!!!!"));
//				.insert(usersService.usersApi())
//				.insert(gamesService.gamesApi())
//				.prefix("score", scoresService.scores());
	}

	private static RatpackServerSpec configuration(RatpackServerSpec server) {
		final Path currentRelativePath = Paths.get("").toAbsolutePath();
		try {
			return server.serverConfig(
					scb
					-> scb
					.baseDir(currentRelativePath)
					.publicAddress(new URI("http://0.0.0.0"))
					.port(9000)
					.threads(4)
			);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private void handleError(final Throwable t) {
		System.err.println(t);
	}

}
