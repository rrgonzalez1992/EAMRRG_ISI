package simulador;

import com.angryelectron.thingspeak.Channel;
import com.angryelectron.thingspeak.Entry;
import com.angryelectron.thingspeak.ThingSpeakException;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Simulador {
	public static void main(String[] args) throws UnirestException,
			ThingSpeakException, InterruptedException {
		String apiWriteKey = "YA7YA97ATWIJ5AU6";
		Channel channel = new Channel(34181, apiWriteKey);
		Autobus[] autobus = new Autobus[8];
		for (int i = 0; i < autobus.length; i++)
			autobus[i] = new Autobus(i + 1);
		while (true) {
			for (int i = 0; i < autobus.length; i++) {
				Entry entry = new Entry();
				autobus[i].simularLocalizacion();
				entry.setField(1, autobus[i].toString());
				channel.update(entry);
				System.out.println("Simulado y añadido a ThingSpeak: "+autobus[i].toString());
				Thread.sleep(16000);
			}
		}
	}
}
