import java.util.*;
import javax.swing.JFrame;
import java.awt.image.*;
import javax.swing.*;
import java.util.EventObject;

public class ArenaConquest extends JFrame
{
  static RPGCharacter player = new RPGCharacter();
  static JFrame frame = new JFrame("Arena Conquest");
  static boolean developer=false;
  
  public static void mainMenu()
  {
    //Opens the main menu 
    int buttonPressed=0;
    JCanvas menuCanvas=new JCanvas();
    frame.setSize(1200,950);
    BufferedImage logo = menuCanvas.loadImage("ArenaConquest logo.png");
    frame.setIconImage(logo);
    BufferedImage menuLogo= menuCanvas.loadImage("ArenaConquest logo.png");
    menuCanvas.drawImage(menuLogo,469,0);
    JButton newGame = new JButton("New Game");
    JButton loadGame = new JButton("Developer");
    JButton options = new JButton("Options");
    JButton exitDesktop = new JButton("Exit To Desktop");
    JBox hOutline;
    JBox subVOutline;
    JBox vOutline = JBox.vbox(menuCanvas,JBox.vspace(50), 
                              hOutline=JBox.hbox(JBox.hglue(), 
                                                 subVOutline=JBox.vbox( newGame,JBox.vspace(50), loadGame,JBox.vspace(50), options,JBox.vspace(50),exitDesktop,JBox.vspace(50)),
                                                 JBox.hglue() ));
    frame.add(vOutline);
    frame.repaint(); 
    frame.setVisible(true);
    JEventQueue events=new JEventQueue();
    events.listenTo(newGame, "newGame");
    events.listenTo(loadGame, "loadGame");
    events.listenTo(options, "options");
    events.listenTo(exitDesktop, "exitDesktop");
    while(buttonPressed==0)
    {
      EventObject event=events.waitEvent();
      String name=events.getName(event);
      if(name.equals("newGame"))
      {
        //begins a new game
        frame.remove(vOutline);
        frame.repaint();
        setStats();
        buttonPressed=1;
      }
      if(name.equals("loadGame"))
      {
        //opens a highlevel game used for bug testing
        developer=true;
        player.name="Nader";
        player.maxHealth=1000;
        player.health=player.maxHealth;
        player.attack=1000;
        player.defence=1000;
        player.speed=1000;
        player.level=10;
        player.powerPoints=10;
        player.gold=100000;
        player.moveSet[0]="Bash";
        player.moveSet[1]="Excalibur";
        player.moveSet[2]="Ragnarok";
        player.moveSet[3]="Lunge";
        frame.remove(vOutline);
        frame.repaint();
        overWorld();
      }
      if(name.equals("options"))
      {
        //displays how the game works
        frame.remove(vOutline);
        frame.repaint();
        options();
        buttonPressed=1;
      }
      if(name.equals("exitDesktop"))
      {
        frame.dispose();
        System.exit(0);
        buttonPressed=1;
        
      }
    }
  }
  public static void options()
  {
    int buttonPressed=0;
    JTextArea howToPlay = new JTextArea("Character Creation: At the beginning of your game you will be presented a character creation screen.\n"+
                                        " You begin with 13 skill points which you will be able to distribute between your four stats: Health, Attack, Defence, and Speed. \n"+
                                        "Each point counts for 40 ability points in that category.(ex: 3 SkillPoints in attack translates to having an attack power of 120)\n"+
                                        "You can gain more SkillPoints by levelling up, you can level up by defeating enemies in the colosseum.\n\n\n" +
                                        "The Colosseum: Once you have created your character you will be put into the colosseum waiting room.\n"+
                                        "In the waiting room you can add more SkillPoints to your character by visiting the Grand Wizard or you can aquire new abilities by visiting the Move Tutour\n"+
                                        "If you're looking for a fight you can head into the arena. There are two game modes within the arena: Gauntlet and free play\n"+
                                        "In Gauntlet you will climb up the ranks in order to fight the champion of the arena. In free play you will fight enemies of increasing skill\n"+
                                        "until you are defeated");
    JButton back=new JButton("Back");
    JBox vOutline = JBox.vbox(howToPlay, JBox.vglue(), back);
    frame.add(vOutline);
    frame.repaint(); 
    frame.setVisible(true);
    JEventQueue events=new JEventQueue();
    events.listenTo(back, "back");
    while(buttonPressed==0)
    {
      EventObject event=events.waitEvent();
      String name=events.getName(event);
      if(name.equals("back"))
      {
        
        frame.remove(vOutline);
        frame.repaint();
        mainMenu();
        buttonPressed=1;
      }
    }
  }
  public static void setStats()
  {
    
    int buttonPressed = 0; 
    JCanvas statCanvas=new JCanvas();
    BufferedImage logo = statCanvas.loadImage("ArenaConquest logo.png");
    frame.setIconImage(logo);
    frame.add(statCanvas);
    frame.setVisible(true);
    BufferedImage characterImage = statCanvas.loadImage("characterOnMenu.gif");
    statCanvas.drawImage(characterImage,0,0);
    JLabel nameHere = new JLabel("Name:");
    JTextField nameText = new JTextField();
    JLabel healthHere = new JLabel("Health:");
    JSlider healthSlider=new JSlider(0, 1, 9, 1);
    healthSlider.setPaintLabels(true);
    healthSlider.setMajorTickSpacing(1);
    healthSlider.setSnapToTicks(true);
    JLabel attackHere = new JLabel("Attack:");
    JSlider attackSlider=new JSlider(0, 1, 9, 1);
    attackSlider.setPaintLabels(true);
    attackSlider.setMajorTickSpacing(1);
    attackSlider.setSnapToTicks(true);
    JLabel defenceHere = new JLabel("Defence:");
    JSlider defenceSlider=new JSlider(0, 1, 9, 1);
    defenceSlider.setPaintLabels(true);
    defenceSlider.setMajorTickSpacing(1);
    defenceSlider.setSnapToTicks(true);
    JLabel speedHere = new JLabel("Speed:");
    JSlider speedSlider=new JSlider(0, 1, 9, 1);
    speedSlider.setPaintLabels(true);
    speedSlider.setMajorTickSpacing(1);
    speedSlider.setSnapToTicks(true);
    JLabel pointCounter = new JLabel("You have 13 points to distribute!");
    JButton finish = new JButton("Finish Character Creation");
    JBox hOutlineName = JBox.hbox(nameHere,JBox.hglue(), nameText,JBox.hglue());
    hOutlineName.setSize(nameText,100,20);
    JBox hOutlineHealth = JBox.hbox(healthHere,JBox.hglue(), healthSlider,JBox.hglue());
    JBox hOutlineAttack = JBox.hbox(attackHere,JBox.hglue(), attackSlider,JBox.hglue());
    JBox hOutlineDefence = JBox.hbox(defenceHere,JBox.hglue(), defenceSlider,JBox.hglue());
    JBox hOutlineSpeed = JBox.hbox(speedHere,JBox.hglue(), speedSlider,JBox.hglue());
    JBox vOutline = JBox.vbox(statCanvas, JBox.vspace(10),
                              pointCounter,
                              hOutlineName,JBox.vspace(10),
                              hOutlineHealth,JBox.vspace(10), 
                              hOutlineAttack,JBox.vspace(10),
                              hOutlineDefence,JBox.vspace(10),
                              hOutlineSpeed,JBox.vspace(10),
                              finish);
    frame.add(vOutline);
    frame.repaint(); 
    frame.setVisible(true);
    JEventQueue events=new JEventQueue();
    events.listenTo(finish, "finish");
    while(buttonPressed==0)
    {
      EventObject event=events.waitEvent();
      String name=events.getName(event);
      if(name.equals("finish"))
      {
        //gets a values inputted by the player
        player.name = nameText.getText();
        player.maxHealth=healthSlider.getValue()*40;
        player.health=player.maxHealth;
        player.attack = attackSlider.getValue()*40;
        player.defence = defenceSlider.getValue()*40;
        player.speed = speedSlider.getValue()*40;
        // if the players stats abide by the rules it will continue
        //otherwise the button wont work
        if(player.maxHealth+player.attack+player.defence+player.speed<=520)
        {
          buttonPressed=1;
          frame.remove(vOutline);
          frame.repaint();
        }
        player.level=1;
        player.exp=0;
        player.gold=0;
        
      }
    }
    //creates the characters main moveset
    player.moveSet[0]="Slash";
    player.moveSet[1]="blank";
    player.moveSet[2]="blank";
    player.moveSet[3]="blank";
  }
  
  public static void battle(RPGCharacter rEnemy, int gameModeDefiner)
  {
    RPGCharacter[] fighter = new RPGCharacter[2];
    Random r = new Random();
    //creates an array holding the two characters that are fighting
    fighter[0] = player;
    fighter[0].health = fighter[0].maxHealth;
    fighter[1] = rEnemy;
    int choseMove=0;
    JCanvas battleCanvas=new JCanvas();
    battleCanvas.setSize(797, 429);
    frame.add(battleCanvas);
    frame.setVisible(true);
    //loads all enemy sprites, holds them in an array
    BufferedImage[] randomEnemySprite = new BufferedImage[15];
    randomEnemySprite[0] = battleCanvas.loadImage("randomEnemy1.png");
    randomEnemySprite[1] = battleCanvas.loadImage("randomEnemy2.png");
    randomEnemySprite[2] = battleCanvas.loadImage("randomEnemy3.png");
    randomEnemySprite[3] = battleCanvas.loadImage("randomEnemy4.png");
    randomEnemySprite[4] = battleCanvas.loadImage("randomEnemy5.png");
    randomEnemySprite[5] = battleCanvas.loadImage("randomEnemy6.png");
    randomEnemySprite[6] = battleCanvas.loadImage("randomEnemy7.png");
    randomEnemySprite[7] = battleCanvas.loadImage("randomEnemy8.png");
    randomEnemySprite[8] = battleCanvas.loadImage("randomEnemy9.png");
    randomEnemySprite[9] = battleCanvas.loadImage("randomEnemy10.png");
    randomEnemySprite[10] = battleCanvas.loadImage("speedBoss.png");
    randomEnemySprite[11] = battleCanvas.loadImage("attackBoss.png");
    randomEnemySprite[12] = battleCanvas.loadImage("defenceBoss.png");
    randomEnemySprite[13] = battleCanvas.loadImage("finalBoss.png");
    randomEnemySprite[14] = battleCanvas.loadImage("finalBosspt2.png");
    //if the developer file is loaded, there are some special sprites for the final boss
    if(developer)
    {
      randomEnemySprite[13] = battleCanvas.loadImage("SephirothSolomon.png");
      randomEnemySprite[14] = battleCanvas.loadImage("Solomonster.png");
    }
    BufferedImage battleScreen = battleCanvas.loadImage("battleScreen.png");
    //displays the users moveset as buttons
    JButton moveOne = new JButton("1:"+player.moveSet[0]);
    JButton moveTwo = new JButton("2:"+player.moveSet[1]);
    JButton moveThree = new JButton("3:"+player.moveSet[2]);
    JButton moveFour = new JButton("4:"+player.moveSet[3]);
    JButton quit = new JButton("5:Run");
    battleCanvas.drawString(fighter[0].name+": "+fighter[0].health+"/"+fighter[0].maxHealth,50, 460);
    battleCanvas.drawString(fighter[1].name+": "+fighter[1].health+"/"+fighter[1].maxHealth,500, 460);
    String nameEvent;
    JBox buttons = JBox.hbox(moveOne,JBox.hspace(5), moveTwo,JBox.hspace(5), moveThree,JBox.hspace(5), moveFour,JBox.hspace(5),quit);
    JBox battleBox = JBox.vbox(battleCanvas, buttons,JBox.vspace(20));
    frame.add(battleBox);
    //if the user is in gauntlet this will decide which boss they face
    int a = r.nextInt(10);
    if(gameModeDefiner==1)
    {
      a=10;
    }
    if(gameModeDefiner==2)
    {
      a=11;
    }
    if(gameModeDefiner==3)
    {
      a=12;
    }
    if(gameModeDefiner==4)
    {
      a=13;
    }
    if(gameModeDefiner==5)
    {
      a=14;
    }
    battleCanvas.drawImage(battleScreen, 0,0 );
    battleCanvas.drawImage(randomEnemySprite[a],600, 74);
    frame.repaint(); 
    frame.setVisible(true);
    String otherMoveUsed = "blank"; 
    while(fighter[0].health>0 && fighter[1].health>0)
    {
      battleCanvas.startBuffer();
      battleCanvas.clear();
      battleCanvas.drawImage(battleScreen, 0,0 );
      battleCanvas.drawImage(randomEnemySprite[a],600, 74);
      
      //selects the enemies move
      do
      {
        int ab= r.nextInt(4);
        otherMoveUsed=rEnemy.moveSet[ab];
      }while(otherMoveUsed=="blank");
      //listens to the users move buttons
      JEventQueue es=new JEventQueue();
      EventObject e;
      es.listenTo(moveOne, "moveOne");
      es.listenTo(moveTwo, "moveTwo");
      es.listenTo(moveThree, "moveThree");
      es.listenTo(moveFour, "moveFour");
      es.listenTo(quit, "run");
      
      do
      {
        
        e=es.waitEvent();
        nameEvent=es.getName(e);
        if(nameEvent.equals("moveOne"))
        {
          if(player.moveSet[0]!="blank")
          {
            //will send the move chosen for both characters to battle method in rpgcharacter
            // that method returns two character in an array that holds their new stats after the exchange
            fighter= fighter[0].battle(fighter[1], player.moveSet[0] , otherMoveUsed);
            //displays the moves used
            battleCanvas.drawString(fighter[0].name+" used "+player.moveSet[0],80,480);
            choseMove=1;
            break;
          }
        }
        else if(nameEvent.equals("moveTwo"))
        {
          if(player.moveSet[1]!="blank")
          {
            
            fighter= fighter[0].battle(fighter[1], player.moveSet[1] , otherMoveUsed);
            battleCanvas.drawString(fighter[0].name+" used "+player.moveSet[1],80,480);
            choseMove=1;
            break;
          }
        }
        else if(nameEvent.equals("moveThree"))
        {
          if(player.moveSet[2]!="blank")
          {
            
            fighter=fighter[0].battle(fighter[1],player.moveSet[2],  otherMoveUsed);
            battleCanvas.drawString(fighter[0].name+" used "+player.moveSet[2],80,480);
            choseMove=1;
            break;
          }
        }
        else if(nameEvent.equals("moveFour"))
        {
          if(player.moveSet[3]!="blank")
          {
            
            fighter=fighter[0].battle(fighter[1],player.moveSet[3],  otherMoveUsed);
            battleCanvas.drawString(fighter[1].name+" used "+player.moveSet[3],80,480);
            choseMove=1;
            break;
          }
        }
        //returns the user back to the overworld if they ran
        else if(nameEvent.equals("run"))
        {
          battleCanvas.endBuffer();
          battleCanvas.clear();
          frame.remove(battleBox);
          frame.repaint();
          overWorld();
        }
      }while(choseMove==0);
      
      choseMove=0;
      //displays what the enemy chose
      battleCanvas.drawString(fighter[1].name+" used "+otherMoveUsed,80,500);
      //displays the health for both characters
      battleCanvas.drawString(fighter[0].name+": "+fighter[0].health+"/"+fighter[0].maxHealth,50, 460);
      battleCanvas.drawString(fighter[1].name+": "+fighter[1].health+"/"+fighter[1].maxHealth,500, 460);
      battleCanvas.endBuffer();
    }
    if(fighter[0].health>0)
    {
      //player will receive gold and skillpoints upon a win
      player.gold=player.gold+500;
      player.exp=player.exp+250;
      if(player.exp>=(500*player.level))
      {
        player.level=player.level+1;
        player.powerPoints=player.powerPoints+1;
      }
      battleCanvas.endBuffer();
      battleCanvas.clear();
      frame.remove(battleBox);
      if(gameModeDefiner==0)
      {
        //if the user is playing freeplay it will send them back to the freeplay method
        freePlay();
      }
      else
      {
        if(gameModeDefiner==5)
        {
          //if the user defeats the final boss
          JCanvas victory = new JCanvas();
         BufferedImage won = victory.loadImage("victory.gif");
          victory.drawImage(won, 0,0);
          frame.repaint();
          JLabel congrats = new JLabel("Congratualtions!!! You defeated the champion of the arena! Here is your reward! 150,000g + 10 Skill Points");
          JButton cont = new JButton("Continue");
          JBox endgame = JBox.vbox(victory,congrats, cont);
          frame.add(endgame);
          frame.repaint(); 
          frame.setVisible(true);
          JEventQueue es=new JEventQueue();
          EventObject e;
          es.listenTo(cont, "continue");
          e=es.waitEvent();
          nameEvent=es.getName(e);
          if(nameEvent.equals("continue"))
          {
            player.gold=player.gold+150000;
            player.powerPoints=player.powerPoints+10;
            frame.remove(endgame);
            frame.repaint();
            overWorld();
          }
        }
      }
    }
    else if(player.health<=0)
    {
      //if the player loses they are sent back to the overworld
      battleCanvas.endBuffer();
      battleCanvas.clear();
      frame.remove(battleBox);
      frame.repaint();
      overWorld();
    }
  }
  
  public static void main(String args[])
  {
    mainMenu();
    overWorld();
  }
  public static void gameModeSelect()
  {
    //sends the user to their desired game mode
    int buttonPressed=0;
    JLabel freePlay = new JLabel("Select freeplay to fight enemies until you lose. Every time you defeat an enemy you will be rewarded gold and experience");
    JLabel gauntlet = new JLabel("Select Gauntlet to fight the best of the best working your way up to fighting the King of the Arena");
    JButton freePlayButton = new JButton("Free Play");
    JButton gauntletButton = new JButton("Gauntlet");
    JBox selectBox = JBox.vbox(freePlay, gauntlet, freePlayButton, gauntletButton);
    frame.add(selectBox);
    frame.repaint(); 
    frame.setVisible(true);
    JEventQueue events=new JEventQueue();
    events.listenTo(freePlayButton, "freePlay");
    events.listenTo(gauntletButton, "gauntlet");
    while(buttonPressed==0)
    {
      EventObject event=events.waitEvent();
      String name=events.getName(event);
      if(name.equals("freePlay"))
      {
        frame.remove(selectBox);
        frame.repaint();
        freePlay();
      }
      if(name.equals("gauntlet"))
      {
        frame.remove(selectBox);
        frame.repaint();
        gauntlet();
      }
    }
    
  }
  public static void gauntlet()
  {
    //creates the 5 bosses that the user faces
    RPGCharacter speedBoss = new RPGCharacter();
    speedBoss.name = "Sylpher the King of Speed";
    speedBoss.maxHealth = 200;
    speedBoss.health=speedBoss.maxHealth;
    speedBoss.attack = 250;
    speedBoss.defence = 120;
    speedBoss.speed = 400;
    speedBoss.level=5;
    speedBoss.moveSet[0]=("Lunge");
    speedBoss.moveSet[1]=("Parry");
    speedBoss.moveSet[2]=("Stab");
    speedBoss.moveSet[3]=("Last Resort");
    
    RPGCharacter attackBoss = new RPGCharacter();
    attackBoss.name = "Akadian the King of Attack";
    attackBoss.maxHealth = 280;
    attackBoss.health=attackBoss.maxHealth;
    attackBoss.attack = 400;
    attackBoss.defence = 150;
    attackBoss.speed = 250;
    attackBoss.level=7;
    attackBoss.moveSet[0]=("Last Resort");
    attackBoss.moveSet[1]=("Charge");
    attackBoss.moveSet[2]=("Lunge");
    attackBoss.moveSet[3]=("Bash");
    
    RPGCharacter defenseBoss = new RPGCharacter();
    defenseBoss.name = "Dianne the Queen of Defense";
    defenseBoss.maxHealth = 300;
    defenseBoss.health=defenseBoss.maxHealth;
    defenseBoss.attack = 120;
    defenseBoss.defence = 400;
    defenseBoss.speed = 40;
    defenseBoss.level=8;
    defenseBoss.moveSet[0]=("Lunge");
    defenseBoss.moveSet[1]=("Bash");
    defenseBoss.moveSet[2]=("Stab");
    defenseBoss.moveSet[3]=("Parry");
    
    RPGCharacter finalBoss = new RPGCharacter();
    finalBoss.name = "King Solomon";
    finalBoss.maxHealth = 700;
    finalBoss.health=finalBoss.maxHealth;
    finalBoss.attack = 450;
    finalBoss.defence = 550;
    finalBoss.speed = 500;
    finalBoss.level=9;
    finalBoss.moveSet[0]=("Rest");
    finalBoss.moveSet[1]=("Stab");
    finalBoss.moveSet[2]=("Last Resort");
    finalBoss.moveSet[3]=("Bash");
    
    
    
    
    
    RPGCharacter finalBossp2 = new RPGCharacter();
    finalBossp2.name = "Solomonster";
    finalBossp2.maxHealth = 1000;
    finalBossp2.health=finalBossp2.maxHealth;
    finalBossp2.attack = 999;
    finalBossp2.defence = 750;
    finalBossp2.speed = 700;
    finalBossp2.level=10;
    finalBossp2.moveSet[0]=("Oblivion");
    finalBossp2.moveSet[1]=("Ragnarok");
    finalBossp2.moveSet[2]=("Excalibur");
    finalBossp2.moveSet[3]=("Starfall");
    
    battle(speedBoss, 1);
    battle(attackBoss, 2);
    battle(defenseBoss, 3);
    battle(finalBoss, 4);
    
    // the 'cutscene' after defeating the first boss phase 
     int buttonPressed=0;
    JLabel partTwo = new JLabel("Announcer: Congratulations!!! The new king of the arena is... ");
    JLabel secDia = new JLabel("???: Not so fast! You haven't defeated me yet");
    JButton cont=new JButton("Continue");
    JBox vOutline = JBox.vbox(partTwo,secDia, JBox.vglue(), cont);
    frame.add(vOutline);
        frame.repaint(); 
    frame.setVisible(true);
    JEventQueue events=new JEventQueue();
    events.listenTo(cont, "cont");
    while(buttonPressed==0)
    {
      EventObject event=events.waitEvent();
      String name=events.getName(event);
      if(name.equals("cont"))
      {
        
        frame.remove(vOutline);
        frame.repaint();
        buttonPressed=1;
      }
    }
    
    battle(finalBossp2, 5);
    overWorld();
  }
  
  public static void freePlay()
  {
    //creates a random enemy and sends the user to fight them
    RPGCharacter rEnemy= new RPGCharacter();
    rEnemy.randomOpponent(player);
    battle(rEnemy,0);
  }
  public static void moveTutour()
  {
    // the shop where the user purchases items
    do{
      String newMove="";
      int buttonPressed=0;
      JLabel wallet = new JLabel("You have "+player.gold+"g");
      JLabel selectMove = new JLabel("Which move would you like to purchase?");
      JLabel stabDef = new JLabel("Stab: Price: 1000g: Deals 30 damage");
      JLabel lungeDef = new JLabel("Lunge: Price 2000g: Deals 15 damage, usually attacks first");
      JLabel bashDef = new JLabel("Bash: Price: 2000g: Deals 50 damage, usually attacks last");
      JLabel chargeDef = new JLabel("Charge: Price: 2500g: Deals 80 damage, lose 50% of your health in recoil");
      JLabel parryDef = new JLabel("Parry: Price: 1500g: Deals 20 damage, uses your opponents attack stat in damage calculations");
      JLabel restDef = new JLabel("Rest: Price: 3000g: Restores 30% of your maximum health");
      JLabel lastResortDef = new JLabel("Last Resort: Price: 5000g: Deals 100 damage, leaves the user with 1 health point");
      JButton stab = new JButton("Stab"); 
      JButton lunge = new JButton("Lunge");
      JButton bash = new JButton("Bash");
      JButton charge = new JButton("Charge");
      JButton parry = new JButton("Parry");
      JButton rest = new JButton("Rest");
      JButton lastResort = new JButton("Last Resort");
      JButton back = new JButton("Back");
      JBox defBox = JBox.vbox(selectMove, JBox.vglue(), stabDef,JBox.vglue(), lungeDef,JBox.vglue(), bashDef,JBox.vglue(), chargeDef,JBox.vglue(), parryDef,JBox.vglue(), restDef,JBox.vglue(), lastResortDef);
      JBox moveBox = JBox.vbox(wallet,JBox.vglue(), stab,JBox.vglue(), lunge,JBox.vglue(), bash,JBox.vglue(), charge,JBox.vglue(), parry,JBox.vglue(), rest,JBox.vglue(), lastResort);
      JBox shopBox = JBox.hbox(defBox, moveBox, back);
      frame.add(shopBox);
      frame.repaint(); 
      frame.setVisible(true);
      JEventQueue events=new JEventQueue();
      events.listenTo(stab, "stab");
      events.listenTo(lunge, "lunge");
      events.listenTo(bash, "bash");
      events.listenTo(charge, "charge");
      events.listenTo(parry, "parry");
      events.listenTo(rest, "rest");
      events.listenTo(lastResort, "lastResort");
      events.listenTo(back, "back");
      while(buttonPressed==0)
      {
        EventObject event=events.waitEvent();
        String name=events.getName(event);
        if(name.equals("stab"))
        {
          if(player.gold>=1000)
          {
            player.gold=player.gold-1000;
            newMove="Stab";
            buttonPressed=1;
          }
        }
        if(name.equals("lunge"))
        {
          if(player.gold>=2000)
          {
            player.gold=player.gold-2000;
            newMove="Lunge";
            buttonPressed=1;
          }
          
        }
        if(name.equals("bash"))
        {
          
          if(player.gold>=2000)
          {
            player.gold=player.gold-2000;
            newMove="Bash";
            buttonPressed=1;
          }
        }
        if(name.equals("charge"))
        {
          
          if(player.gold>=2500)
          {
            player.gold=player.gold-2500;
            newMove="Charge";
            buttonPressed=1;
          }
        }
        if(name.equals("parry"))
        {
          
          if(player.gold>=1500)
          {
            player.gold=player.gold-1500;
            newMove="Parry";
            buttonPressed=1;
          }
        }
        if(name.equals("rest"))
        {
          
          if(player.gold>=3000)
          {
            player.gold=player.gold-3000;
            newMove="Rest";
            buttonPressed=1;
          }
        }
        if(name.equals("lastResort"))
        {
          
          if(player.gold>=5000)
          {
            player.gold=player.gold-5000;
            newMove="Last Resort";
            buttonPressed=1;
          }
        }
        if(name.equals("back"))
        {
          frame.remove(shopBox);
          frame.repaint();
          buttonPressed=1;
          overWorld();
        }
      }
      frame.remove(shopBox);
      frame.repaint();
      JLabel replaceMove = new JLabel("Which move would you like to replace?");
      JButton firstMove = new JButton(player.moveSet[0]);
      JButton secMove = new JButton(player.moveSet[1]);
      JButton thirdMove = new JButton(player.moveSet[2]);
      JButton fourthMove = new JButton(player.moveSet[3]);
      JBox chooseMove = JBox.vbox(replaceMove, firstMove, secMove, thirdMove,fourthMove);
      frame.add(chooseMove);
      frame.repaint(); 
      frame.setVisible(true);
      buttonPressed=0;
      JEventQueue es=new JEventQueue();
      es.listenTo(firstMove, "firstMove");
      es.listenTo(secMove, "secMove");
      es.listenTo(thirdMove, "thirdMove");
      es.listenTo(fourthMove, "fourthMove");
      while(buttonPressed==0)
      {
        EventObject e=es.waitEvent();
        String name=es.getName(e);
        if(name.equals("firstMove"))
        {
          player.moveSet[0]=newMove;
          frame.remove(chooseMove);
          frame.repaint();
          buttonPressed=1;
        }
        if(name.equals("secMove"))
        {
          player.moveSet[1]=newMove;
          frame.remove(chooseMove);
          frame.repaint();
          buttonPressed=1;
        }
        if(name.equals("thirdMove"))
        {
          player.moveSet[2]=newMove;
          frame.remove(chooseMove);
          frame.repaint();
          buttonPressed=1;
        }
        if(name.equals("fourthMove"))
        {
          player.moveSet[3]=newMove;
          frame.remove(chooseMove);
          frame.repaint();
          buttonPressed=1;
        }
      }
    }while(true);
  }
  public static void grandWizard()
  {
    //the shop where the user upgrades stats
    do{
      JLabel improveStat = new JLabel("Which stat would you like to improve?");
      int buttonPressed=0;
      JButton hp = new JButton("Health");
      JButton attack = new JButton("Attack");
      JButton defence = new JButton("Defence");
      JButton speed = new JButton("Speed");
      JButton back = new JButton("Back");
      JLabel powerPointCounter = new JLabel("You have "+player.powerPoints+" Skill Points");
      JBox pointShop = JBox.vbox(powerPointCounter, improveStat, hp, attack ,defence,speed, back );
      frame.add(pointShop);
      frame.repaint(); 
      frame.setVisible(true);
      JEventQueue events=new JEventQueue();
      events.listenTo(hp, "hp");
      events.listenTo(attack, "attack");
      events.listenTo(defence, "defence");
      events.listenTo(speed, "speed");
      events.listenTo(back, "back");
      while(buttonPressed==0)
      {
        EventObject event=events.waitEvent();
        String name=events.getName(event);
        if(name.equals("hp"))
        {
          if(player.powerPoints>=1)
          {
            player.powerPoints=player.powerPoints-1;
            player.maxHealth=player.maxHealth+40;
            frame.remove(pointShop);
            frame.repaint();
            buttonPressed=1;
          }
        }
        if(name.equals("attack"))
        {
          if(player.powerPoints>=1)
          {
            player.powerPoints=player.powerPoints-1;
            player.attack=player.attack+40;
            frame.remove(pointShop);
            frame.repaint();
            buttonPressed=1;
          }
        }
        if(name.equals("defence"))
        {
          if(player.powerPoints>=1)
          {
            player.powerPoints=player.powerPoints-1;
            player.defence=player.defence+40;
            frame.remove(pointShop);
            frame.repaint();
            buttonPressed=1;
          }
        }
        if(name.equals("speed"))
        {
          if(player.powerPoints>=1)
          {
            player.powerPoints=player.powerPoints-1;
            player.speed=player.speed+40;
            frame.remove(pointShop);
            frame.repaint();
            buttonPressed=1;
          }
        }
        if(name.equals("back"))
        {
          frame.remove(pointShop);
          frame.repaint();
          overWorld();
        }
      }
      
    }while(true);
  }
  public static void overWorld()
  {
    //the over world, the user can control the characters walking path
    int x=280;
    int y=300;
    int step =0;
    JCanvas canvas = new JCanvas();
    BufferedImage map = canvas.loadImage("ArenaConquestMap.png");
    BufferedImage charUpStill = canvas.loadImage("charUpStill.png");
    BufferedImage charUp1 = canvas.loadImage("charUp1.png");
    BufferedImage charUp2 = canvas.loadImage("charUp2.png");
    BufferedImage charDownStill = canvas.loadImage("charDownStill.png");
    BufferedImage charDown1 = canvas.loadImage("charDown1.png");
    BufferedImage charDown2 = canvas.loadImage("charDown2.png");
    BufferedImage charRightStill = canvas.loadImage("charRightStill.png");
    BufferedImage charRight1 = canvas.loadImage("charRight1.png");
    BufferedImage charRight2 = canvas.loadImage("charRight2.png");
    BufferedImage charLeftStill = canvas.loadImage("charLeftStill.png");
    BufferedImage charLeft1 = canvas.loadImage("charLeft1.png");
    BufferedImage charLeft2 = canvas.loadImage("charLeft2.png");
    
    canvas.drawImage(map,0,0);
    canvas.drawImage(charUpStill,x,y);
    JButton upButton = new JButton("^");
    JButton downButton = new JButton("v");
    JButton rightButton = new JButton(">");
    JButton leftButton = new JButton("<");
    JBox upBox = JBox.hbox(JBox.hspace(40), upButton);
    JBox downBox = JBox.hbox(JBox.hspace(40), downButton);
    JBox horButtons = JBox.hbox(leftButton, JBox.hspace(40), rightButton);
    JBox dPad = JBox.vbox(upBox,horButtons,downBox);
    JBox overWorldBox= JBox.vbox(canvas, dPad);
    frame.add(overWorldBox);
    frame.repaint(); 
    frame.setVisible(true);
    JEventQueue events=new JEventQueue();
    events.listenTo(upButton, "move up");
    events.listenTo(downButton, "move down");
    events.listenTo(leftButton, "move left");
    events.listenTo(rightButton, "move right");
    while(true)
    {
      canvas.startBuffer();
      EventObject event=events.waitEvent();
      String name=events.getName(event);
      if(name.equals("move up"))
      {
        canvas.clear();
        canvas.drawImage(map,0,0);
        if(step==0)
        {
          canvas.drawImage(charUp1,x,y);
          y=y-10;
          step++;
        }
        else if(step==1)
        {
          canvas.drawImage(charUp2,x,y);
          y=y-10;
          step--;
        }
        if(y<=48)
        {
          y=48;
        }
        if(y>=40 && y<=130 && x>=101 && x<=206)
        {
          canvas.clear();
          canvas.endBuffer();
          frame.remove(overWorldBox);
          frame.repaint();
          grandWizard();
          y=y+10;
        }
        if(y>=40 && y<=130 && x>=388 && x<=472)
        {
          canvas.clear();
          canvas.endBuffer();
          frame.remove(overWorldBox);
          frame.repaint();
          moveTutour();
          y=y+10;
        }
        if(y<= 50 && x>=255 && x<=327)
        {
          canvas.clear();
          canvas.endBuffer();
          frame.remove(overWorldBox);
          frame.repaint();
          gameModeSelect();
          
        }
        
      }
      if(name.equals("move down"))
      {
        canvas.clear();
        canvas.drawImage(map,0,0);
        if(step==0)
        {
          canvas.drawImage(charDown1,x,y); 
          y=y+10;
          step++;
        }
        
        else if(step==1)
        {
          canvas.drawImage(charDown2,x,y);
          y=y+10;
          step--;
        }
        if(y>=345)
        {
          y=345;
        }
        if(y>=40 && y<=130 && x>=101 && x<=206)
        {
          canvas.clear();
          canvas.endBuffer();
          frame.remove(overWorldBox);
          frame.repaint();
          grandWizard();
          y=y-10;
        }
        if(y>=40 && y<=130 && x>=388 && x<=472)
        {
          canvas.clear();
          canvas.endBuffer();
          frame.remove(overWorldBox);
          frame.repaint();
          moveTutour();
          y=y-10;
        }
      }
      if(name.equals("move left"))
      {
        canvas.clear();
        canvas.drawImage(map,0,0);
        if(step==0)
        {
          canvas.drawImage(charLeft1,x,y);
          x=x-10;
          step++;
        }
        else if(step==1)
        {
          canvas.drawImage(charLeft2,x,y);
          x=x-10;
          step--;
        }
        if(x<=101)
        {
          x=101;
        }
        if(y>=40 && y<=130 && x>=101 && x<=206)
        {
          canvas.clear();
          canvas.endBuffer();
          frame.remove(overWorldBox);
          frame.repaint();
          grandWizard();
          x=x+10;
        }
        if(y>=40 && y<=130 && x>=388 && x<=472)
        {
          canvas.clear();
          canvas.endBuffer();
          frame.remove(overWorldBox);
          frame.repaint();
          moveTutour();
          x=x+10;
        }
      }
      if(name.equals("move right"))
      {
        canvas.clear();
        canvas.drawImage(map,0,0);
        if(step==0)
        {
          canvas.drawImage(charRight1,x,y);
          x=x+10;
          step++;
        }
        else if(step==1)
        {
          canvas.drawImage(charRight2,x,y);
          x=x+10;
          step--;
        }
        if(x>=452)
        {
          x=452;
        }
        if(y>=40 && y<=130 && x>=101 && x<=206)
        {
          canvas.clear();
          canvas.endBuffer();
          frame.remove(overWorldBox);
          frame.repaint();
          grandWizard();
          x=x-10;
        }
        if(y>=40 && y<=130 && x>=388 && x<=472)
        {
          canvas.clear();
          canvas.endBuffer();
          frame.remove(overWorldBox);
          frame.repaint();
          moveTutour();
          x=x-10;
        }
      }
      canvas.endBuffer();
    }
  }
}