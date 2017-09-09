package commands;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class StartCommand extends BotCommand {

    public StartCommand() {super("start", "With this command you can start the Bot");}

    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        Properties properties = new Properties();
        FileInputStream fis;

        StringBuilder messageBuilder = new StringBuilder();

        String userName = (user.getFirstName() == null? "": user.getFirstName()) +
                (user.getLastName() == null?"": " "+ user.getLastName());
        messageBuilder.append(EmojiParser.parseToUnicode("Привет, " + userName + "!:vulcan_salute:\n\n"));
        messageBuilder.append((EmojiParser.parseToUnicode("Этот бот создан для помощи в изучении SQL.\n\n")));
        messageBuilder.append("Он содержит лекционный и практический материал, просматривая и решая который, ты будешь совершенствовать свои навыки в области Database.\n\n");
        messageBuilder.append("Практические задания подразумивают что ты уже промотрел соответствующий лекционный материал, и в онсновном будут опираться на него.\n\n");
        messageBuilder.append("Воспользуйся командой /help чтобы посмотреть полное описание команд. \n\n");
        messageBuilder.append((EmojiParser.parseToUnicode("Ну вот и все, вперед навстречу знаниям! И помни - главное систематичность занятий. \nУспехов!:wink:")));



        try {
            fis = new FileInputStream("src/main/resources/descriptionCommand.properties");
            properties.load(fis);

            String description = new String(properties.getProperty("startCommand").getBytes("ISO8859-1"));
            SendMessage answer = new SendMessage();
            answer.setChatId(chat.getId().toString());
            answer.setText("Привет, " + userName + description);


            absSender.sendMessage(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
