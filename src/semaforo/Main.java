package semaforo;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		int numberofPermicoes = 2;
		int numberofProcesses = 6;

		Semaphore semaphore = new Semaphore(numberofPermicoes);
		ProcessorThread[] processos = new ProcessorThread[numberofProcesses];

		for (int i = 0; i < numberofProcesses; i++) {
			processos[i] = new ProcessorThread(i, semaphore);
			processos[i].start();
		}
	}

}
