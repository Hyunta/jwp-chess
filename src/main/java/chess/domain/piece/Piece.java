package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Position;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class Piece {
    protected static final String INVALID_TARGET_POSITION_EXCEPTION = "이동할 수 없는 위치입니다.";

    private final Color color;
    private final Type type;

    protected Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public final boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public final boolean isSameColorWith(Piece targetPiece) {
        return this.color == targetPiece.color;
    }

    public final boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isBlank() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public Color getColor() {
        return color;
    }

    public abstract void move(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction);

    public abstract void capture(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction);

    public abstract double getScore();

    public abstract String getSymbol();

    protected abstract boolean canMove(Position beforePosition, Position afterPosition);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
