/*    */ package engine.core;
/*    */ 
/*    */ import engine.helper.Assets;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.GraphicsConfiguration;
/*    */ import java.awt.Image;
/*    */ import java.awt.event.FocusEvent;
/*    */ import java.awt.event.FocusListener;
/*    */ import javax.swing.JComponent;
/*    */ 
/*    */ public class MarioRender
/*    */   extends JComponent
/*    */   implements FocusListener
/*    */ {
/*    */   private static final long serialVersionUID = 790878775993203817L;
/*    */   public static final int TICKS_PER_SECOND = 24;
/*    */   private float scale;
/*    */   private GraphicsConfiguration graphicsConfiguration;
/*    */   int frame;
/*    */   Thread animator;
/*    */   boolean focused;
/*    */   
/*    */   public MarioRender(float scale) {
/* 25 */     setFocusable(true);
/* 26 */     setEnabled(true);
/* 27 */     this.scale = scale;
/*    */     
/* 29 */     Dimension size = new Dimension((int)(256.0F * scale), (int)(240.0F * scale));
/*    */     
/* 31 */     setPreferredSize(size);
/* 32 */     setMinimumSize(size);
/* 33 */     setMaximumSize(size);
/*    */     
/* 35 */     setFocusable(true);
/*    */   }
/*    */   
/*    */   public void init() {
/* 39 */     this.graphicsConfiguration = getGraphicsConfiguration();
/* 40 */     Assets.init(this.graphicsConfiguration);
/*    */   }
/*    */   
/*    */   public void renderWorld(MarioWorld world, Image image, Graphics g, Graphics og) {
/* 44 */     og.fillRect(0, 0, 256, 240);
/* 45 */     world.render(og);
/* 46 */     drawStringDropShadow(og, "Lives: " + world.lives, 0, 0, 7);
/* 47 */     drawStringDropShadow(og, "Coins: " + world.coins, 11, 0, 7);
/* 48 */     drawStringDropShadow(og, "Time: " + ((world.currentTimer == -1) ? "Inf" : String.valueOf((int)Math.ceil((world.currentTimer / 1000.0F)))), 22, 0, 7);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 58 */     if (this.scale > 1.0F) {
/* 59 */       g.drawImage(image, 0, 0, (int)(256.0F * this.scale), (int)(240.0F * this.scale), null);
/*    */     } else {
/* 61 */       g.drawImage(image, 0, 0, null);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void drawStringDropShadow(Graphics g, String text, int x, int y, int c) {
/* 66 */     drawString(g, text, x * 8 + 5, y * 8 + 5, 0);
/* 67 */     drawString(g, text, x * 8 + 4, y * 8 + 4, c);
/*    */   }
/*    */   
/*    */   private void drawString(Graphics g, String text, int x, int y, int c) {
/* 71 */     char[] ch = text.toCharArray();
/* 72 */     for (int i = 0; i < ch.length; i++) {
/* 73 */       g.drawImage(Assets.font[ch[i] - 32][c], x + i * 8, y, null);
/*    */     }
/*    */   }
/*    */   
/*    */   public void focusGained(FocusEvent arg0) {
/* 78 */     this.focused = true;
/*    */   }
/*    */   
/*    */   public void focusLost(FocusEvent arg0) {
/* 82 */     this.focused = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\hbsss\Downloads\MFEDRL-master\MFEDRL-master\Mario-AI-Framework.jar!\engine\core\MarioRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */