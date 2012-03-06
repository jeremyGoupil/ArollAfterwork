package fr.arolla.afterwork.core;


import android.graphics.Canvas;
import android.graphics.Rect;
import fr.arolla.afterwork.util.TypeSpriteEnum;

public class HumanSprite extends Sprite {
    public HumanSprite(Rect spriteArea) {
        super(spriteArea);
    }

    @Override
    public TypeSpriteEnum getTypeId() {
        return TypeSpriteEnum.HUMAN;
    }

    @Override
    public void paint(Canvas c, double scale, int dx, int dy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
