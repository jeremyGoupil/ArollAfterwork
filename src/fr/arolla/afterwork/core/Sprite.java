package fr.arolla.afterwork.core;


import android.graphics.*;
import android.os.Bundle;
import fr.arolla.afterwork.util.TypeSpriteEnum;

import java.util.Vector;

public abstract class Sprite {

    private Rect spriteArea;
    private int saved_id;

    public Sprite(Rect spriteArea) {
        this.spriteArea = spriteArea;
        saved_id = -1;
    }

    public void saveState(Bundle map, Vector saved_sprites) {
        if (saved_id != -1) {
            return;
        }
        saved_id = saved_sprites.size();
        saved_sprites.addElement(this);
        map.putInt(String.format("%d-left", saved_id), spriteArea.left);
        map.putInt(String.format("%d-right", saved_id), spriteArea.right);
        map.putInt(String.format("%d-top", saved_id), spriteArea.top);
        map.putInt(String.format("%d-bottom", saved_id), spriteArea.bottom);
        map.putInt(String.format("%d-type", saved_id), getTypeId().ordinal());
    }

    public final int getSavedId() {
        return saved_id;
    }

    public final void clearSavedId() {
        saved_id = -1;
    }

    public abstract TypeSpriteEnum getTypeId();

    public void changeSpriteArea(Rect newArea) {
        spriteArea = newArea;
    }

    public final void relativeMove(Point p) {
        spriteArea = new Rect(spriteArea);
        spriteArea.offset(p.x, p.y);
    }

    public final void relativeMove(int x, int y) {
        spriteArea = new Rect(spriteArea);
        spriteArea.offset(x, y);
    }

    public final void absoluteMove(Point p) {
        spriteArea = new Rect(spriteArea);
        spriteArea.offsetTo(p.x, p.y);
    }

    public final Point getSpritePosition() {
        return new Point(spriteArea.left, spriteArea.top);
    }

    public final Rect getSpriteArea() {
        return spriteArea;
    }

    public static void drawImage(Bitmap image, int x, int y,
                                 Canvas c, double scale, int dx, int dy) {
        c.drawBitmap(image, (float) (x * scale + dx), (float) (y * scale + dy),
                null);
    }

    public static void drawImageClipped(Bitmap image, int x, int y, Rect clipr,
                                        Canvas c, double scale, int dx, int dy) {
        c.save(Canvas.CLIP_SAVE_FLAG);
        c.clipRect((float) (clipr.left * scale + dx),
                (float) (clipr.top * scale + dy),
                (float) (clipr.right * scale + dx),
                (float) (clipr.bottom * scale + dy),
                Region.Op.REPLACE);
        c.drawBitmap(image, (float) (x * scale + dx), (float) (y * scale + dy),
                null);
        c.restore();
    }

    public abstract void paint(Canvas c, double scale, int dx, int dy);
}
