
package es.programacinoextrema.discord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.imageio.stream.FileImageInputStream;

import org.tensorflow.TensorFlow;

import es.programacinoextrema.Configuracion;
import es.programacinoextrema.bot.*;
import es.programacinoextrema.tensorflow.*;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.data.stored.UserBean;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import es.programacinoextrema.bot.Bot;
import es.programacinoextrema.tensorflow.RNN1;

public class Discord{
	public static void main(String arg[]) {
		//
	    
		/*RNN1 red=new RNN1();
		red.iniciar();
		
		if(1==1) {
			return ;
		}*/
		
		final DiscordClient cliente=new DiscordClientBuilder(Configuracion.parametro("TOKEN")).build();
		String patronMencionado="(<@!\\d*>) ?"; 
		cliente.getEventDispatcher().on(ReadyEvent.class)
        .subscribe(ready -> System.out.println("Logged in as " + ready.getSelf().getUsername()));

		/*cliente.getEventDispatcher().on(MessageCreateEvent.class)
        .map(MessageCreateEvent::getMessage)
        .filter(msg -> msg.getContent().map("!ping"::equals).orElse(false))
        .flatMap(Message::getChannel)
        .flatMap(channel -> channel.createMessage("Pong!"))
        .subscribe();/**/
		User anonimo=cliente.getSelf().block();
		cliente.getEventDispatcher().on(MessageCreateEvent.class).subscribe(
			evento->{
				Message mensajeObjeto=evento.getMessage();
				final String mensaje=mensajeObjeto.getContent().orElse("").replaceAll(patronMencionado, "");
				if(mensaje.equals("!ping")) {
					evento.getMessage().getChannel().subscribe(canal->canal.createMessage("Pong!").block());
				}
				if(mensaje!="") {
					System.out.println(mensaje);
					
					User usuario= mensajeObjeto.getAuthor().orElse(anonimo);
					System.out.println("Usuario que manda el mensaje "+usuario.getUsername());
					mensajeObjeto.getChannel().subscribe(canal->{
						mensajeObjeto.getUserMentions().subscribe(usuarioMencionado->{
							System.out.println("escribo");
							System.out.println("Usuario mencionado "+usuarioMencionado.getUsername());	
							if(usuarioMencionado.getUsername().equals("bot twitch")) {
								canal.createMessage(Bot.procesar(mensaje)).block();
							}
						});
					});
					
					
				}
			}
		);
		cliente.login().block();
		
	}
}