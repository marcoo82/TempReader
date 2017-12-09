package dev.maroo;

import dev.maroo.temp.repo.TempRepositoryImpl;

/**
 *
 * @author Maroo
 */
public class Main {

	public static void main(String[] args) throws Exception {
		Server server = new Server(new TempRepositoryImpl());
		server.start();
	}

}
