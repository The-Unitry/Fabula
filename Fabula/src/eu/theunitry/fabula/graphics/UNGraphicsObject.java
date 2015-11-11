package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.objects.UNObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class UNGraphicsObject
    {

        private JFrame frame;
        private int x;
        private int y;
        private Image image;
        private boolean clickable;
        private Rectangle hitbox;
        private boolean mouseHold;
        private int width, height;
        private int xOffset, yOffset;

        public UNGraphicsObject(JFrame frame, int x, int y, Image image, boolean clickable, int width, int height)
        {
            this.frame = frame;
            this.x = x;
            this.y = y;
            this.image = image;
            this.clickable = clickable;
            this.hitbox = new Rectangle(x, y, width, height);
            this.mouseHold = false;
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
        }

        public int getX()
        {
            return this.x;
        }

        public void setX(int x)
        {
            x = Math.max(0, Math.min(frame.getContentPane().getWidth() - this.getWidth(), x));
            this.x = x;
        }

        public int getY()
        {
            return this.y;
        }

        public void setY(int y)
        {
            y = Math.max(0, Math.min(frame.getContentPane().getHeight() - this.getHeight(), y));
            this.y = y;
        }

        public Image getImage()
        {
            return this.image;
        }

        public void setImage(Image image)
        {
            this.image = image;
        }

        public boolean getClickable()
        {
            return this.clickable;
        }

        public void setClickable(boolean clickable)
        {
            this.clickable = clickable;
        }

        public Rectangle getHitbox()
        {
            return this.hitbox;
        }

        public void setHitbox(Rectangle hitbox)
        {
            this.hitbox = hitbox;
        }

        public int getWidth()
        {
            return this.width;
        }

        public void setWidth(int width)
        {
            this.width = width;
        }

        public int getHeight()
        {
            return this.height;
        }

        public void setHeight(int height)
        {
            this.height = height;
        }

        public void setMouseHold(boolean mouseHold) {
            this.mouseHold = mouseHold;
        }

        public boolean getMouseHold() {
            return this.mouseHold;
        }

        public void setXOffset(int xOffset) {
            this.xOffset = xOffset;
        }

        public int getXOffset() {
            return this.xOffset;
        }

        public void setYOffset(int yOffset) {
            this.yOffset = yOffset;
        }

        public int getYOffset() {
            return this.yOffset;
        }
    }
