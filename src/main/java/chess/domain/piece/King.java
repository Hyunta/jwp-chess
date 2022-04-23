package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class King extends Piece {
    private static final int MOVABLE_DISTANCE = 1;
    private static final double SCORE = 0;

    public King(Color color) {
        super(color, Type.KING);
    }

    @Override
    public void move(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        if (!canMove(beforePosition, afterPosition)) {
            throw new IllegalArgumentException(INVALID_TARGET_POSITION_EXCEPTION);
        }
        moveFunction.accept(this);
    }

    @Override
    public void capture(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        this.move(beforePosition, afterPosition, moveFunction);
    }

    @Override
    protected boolean canMove(Position beforePosition, Position afterPosition) {
        int columnDistance = beforePosition.columnDistance(afterPosition);
        int rowDistance = beforePosition.rowDistance(afterPosition);
        if (columnDistance + rowDistance == MOVABLE_DISTANCE) {
            return true;
        }
        return columnDistance == MOVABLE_DISTANCE && rowDistance == MOVABLE_DISTANCE;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String getSymbol() {
        return "k";
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
