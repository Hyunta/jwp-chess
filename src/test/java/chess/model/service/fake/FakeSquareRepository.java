package chess.model.service.fake;

import chess.model.piece.Piece;
import chess.model.square.Square;
import chess.repository.SquareRepository;

import java.util.List;
import java.util.Map;

public class FakeSquareRepository implements SquareRepository<Square> {

    @Override
    public Square save(Square square) {
        return null;
    }

    @Override
    public Square getBySquareAndBoardId(Square square, int boardId) {
        return null;
    }

    @Override
    public int saveAllSquare(int boardId) {
        return 0;
    }

    @Override
    public Map<Square, Piece> findAllSquaresAndPieces(int boardId) {
        return null;
    }

    @Override
    public List<Square> getPaths(List<Square> squares, int roomId) {
        return null;
    }
}
