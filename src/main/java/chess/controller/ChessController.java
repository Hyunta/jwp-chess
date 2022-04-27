package chess.controller;

import chess.dto.RoomRequestDto;
import chess.dto.ResponseDto;
import chess.dto.ScoreDto;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import chess.model.room.Room;
import chess.service.ChessService;

@Controller
public class ChessController {

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("rooms", chessService.getRooms());
        System.out.println("here");
        System.out.println(chessService.getRooms());
        return "home";
    }

    @PostMapping(value = "/room")
    public String room(@ModelAttribute RoomRequestDto roomDto) {
        Room room = chessService.init(
                roomDto.getRoomName(),
                roomDto.getPassword(),
                roomDto.getWhiteName(),
                roomDto.getBlackName());

        return "redirect:/room/" + room.getId();
    }

    @GetMapping("/room/{roomId}")
    public String room(Model model, @PathVariable(value = "roomId") String roomId) {
        model.addAttribute("roomId", Integer.parseInt(roomId));
        model.addAttribute("board", chessService.getBoard(Integer.parseInt(roomId)));
        return "chess-game";
    }

    @PostMapping("/room/{roomId}/move")
    @ResponseBody
    public String move(@RequestBody String messageBody, @PathVariable String roomId) {
        final String[] split = messageBody.strip().split("=")[1].split(" ");
        String source = split[0];
        String target = split[1];
        try {
            chessService.move(source, target, Integer.parseInt(roomId));
        } catch (IllegalArgumentException e) {
            return ResponseDto.of(HttpStatus.BAD_REQUEST_400, e.getMessage(),
                    chessService.isEnd(Integer.parseInt(roomId))).toString();
        }
        return ResponseDto.of(HttpStatus.OK_200, null, chessService.isEnd(Integer.parseInt(roomId))).toString();
    }

    @GetMapping("/room/{roomId}/status")
    @ResponseBody
    public String status(@PathVariable(value = "roomId") String roomId) {
        return ScoreDto.from(chessService.status(Integer.parseInt(roomId))).toString();
    }

    @PostMapping("/room/{roomId}/end")
    public String end(Model model, @PathVariable int roomId) {
        model.addAttribute("result", ScoreDto.from(chessService.status(roomId)));
        chessService.end(roomId);
        return "result";
    }
}
