package commands;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static javafx.scene.input.KeyCode.U;

/**
 * Created by Victor on 22.03.2017.
 */
public class StartCommand extends BotCommand {

   // public static final String LOGTAG = "STARTCOMMAND";

    public StartCommand() {super("start", "With this command you can start the Bot");}

    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        //TODO: Create new SUPER PUPER start message.

        StringBuilder messageBuilder = new StringBuilder();

       //char[] smile = Character.toChars("\ud83d");

        String userName = (user.getFirstName() == null? "": user.getFirstName()) +
                 (user.getLastName() == null?"": " "+ user.getLastName());
        messageBuilder.append(EmojiParser.parseToUnicode("Привет, " + userName + "!:vulcan_salute:\n\n"));
        messageBuilder.append((EmojiParser.parseToUnicode("Этот бот - твоя последняя надежда выучить SQL.\nНе сможет научить он - не сможет никто!(It's a joke:smile:)\n\n")));
        messageBuilder.append("Воспользуйся командой /help чтобы посмотреть описание команд. \n\n");
        messageBuilder.append((EmojiParser.parseToUnicode("Скачивай лекции, проходи задания и у тебя все получится!Успехов!:wink:")));

        SendMessage answer = new SendMessage();
        answer.setChatId(chat.getId().toString());
        answer.setText(messageBuilder.toString());

        try {
            absSender.sendMessage(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
