import java.util.*;
public class RPGCharacter
{
  //character fields
  String name;
  int level;
  int exp;
  int maxHealth;
  int health=maxHealth;
  int attack;
  int defence;
  int speed;
  String[] moveSet = new String[4];
  int gold;
  int powerPoints;
  //the move methods
  public void slash(RPGCharacter other)
  {
    double damage=((double)this.attack/(double)other.defence)*(20+(this.level*5)) ;
    other.health=other.health-(int)damage;
  }
  public void lunge(RPGCharacter other)
  {
    double damage=((double)this.attack/(double)other.defence)*(15+(this.level*5)) ;
    other.health=other.health-(int)damage;
  }
  public void lungeSet()
  {
    this.speed=this.speed+1000;
  }
  public void lungeRecover()
  {
    this.speed=this.speed-1000;
  }
  public void parry(RPGCharacter other)
  {
    double damage =((double)other.attack/(double)other.defence)*(20+(this.level*5));
    other.health=other.health-(int)damage;
  }
  public void stab(RPGCharacter other)
  {
    double damage=((double)this.attack/(double)other.defence)*(30+(this.level*5)) ;
    other.health=other.health-(int)damage;
  }
  public void bash(RPGCharacter other)
  {
    double damage=((double)this.attack/(double)other.defence)*(50+(this.level*5)) ;
    other.health=other.health-(int)damage;
  }
  public void bashSet()
  {
    this.speed=this.speed-1000;
  }
  public void bashRecover()
  {
    this.speed=this.speed+1000;
  }
  public void charge(RPGCharacter other)
  {
    double damage=((double)this.attack/(double)other.defence)*(80+(this.level*5)) ;
    other.health=other.health-(int)damage;
    this.health=this.health-(this.maxHealth/2);
  }
  public void rest()
  {
    double restore = this.maxHealth*0.3;
    this.health=this.health+(int)restore;
    if(this.health>this.maxHealth)
    {
      this.health=this.maxHealth;
    }
  }
  public void lastResort(RPGCharacter other )
  {
    double damage=((double)this.attack/(double)other.defence)*(100+(this.level*5)) ;
    other.health=other.health-(int)damage;
    this.health=1;
  }
  
  public void oblivion(RPGCharacter other)
  {
    Random r = new Random();
    if(r.nextInt(50)==7)
    {
      other.health = other.health-1000;
    }
    
  }
  
  public void starfall(RPGCharacter other)
  {
    Random r = new Random();
    int a=r.nextInt(5)+1;
    double damage=((double)this.attack/(double)other.defence)*(20+(this.level*5))*a;
    other.health=other.health-(int)damage;
  }
  
  public void ragnarok(RPGCharacter other)
  {
    double damage=((double)this.attack/(double)other.defence)*(100+(this.level*5)) ;
    other.health=other.health-(int)damage;
  }
  
  public void excalibur(RPGCharacter other)
  {
    double damage=((double)this.attack/(double)other.defence)*(30+(this.level*5)) ;
    other.health=other.health-(int)damage;
    this.health=this.health+(int)damage;
     if(this.health>this.maxHealth)
        {
        this.health=this.maxHealth;
        }
  }
  public RPGCharacter[] battle(RPGCharacter other,String playerMoveUsed , String otherMoveUsed)
  {
    RPGCharacter[] fighters = new RPGCharacter[2];
    fighters[0]=this;
    fighters[1]=other;
    boolean playerLungeUsed=false;
    boolean otherLungeUsed=false;
    boolean playerBashUsed=false;
    boolean otherBashUsed=false;
    //for moves that change attack order
    if(playerMoveUsed.equals("Lunge"))
    {
      this.lungeSet();
      playerLungeUsed=true;
    }
    else if(playerMoveUsed.equals("Bash"))
    {
      this.bashSet();
      playerBashUsed=true;
    }
    if(otherMoveUsed.equals("Lunge"))
    {
      other.lungeSet();
      otherLungeUsed=true;
    }
    else if(otherMoveUsed.equals("Bash"))
    {
      other.bashSet();
      otherBashUsed=true;
    }
    //if the user is faster than the other character
    if(this.speed>other.speed)
    {
      if(playerMoveUsed.equals("Lunge"))
      {
        this.lunge(other);
      }
      else if(playerMoveUsed.equals("Bash"))
      {
        this.bash(other);
      }
      else if(playerMoveUsed.equals("Slash"))
      {
        this.slash(other);
      }
      else if(playerMoveUsed.equals("Stab"))
      {
        this.stab(other);
      }
      else if(playerMoveUsed.equals("Parry"))
      {
        this.parry(other);
      }
      else if(playerMoveUsed.equals("Charge"))
      {
        this.charge(other);
      }
      else if(playerMoveUsed.equals("Last Resort"))
      {
        this.lastResort(other);
      }
      else if(playerMoveUsed.equals("Rest"))
      {
        this.rest();
      }
      else if(playerMoveUsed.equals("Oblivion"))
      {
        this.oblivion(other);
      }
      else if(playerMoveUsed.equals("Starfall"))
      {
        this.starfall(other);
      }
      else if(playerMoveUsed.equals("Excalibur"))
      {
        this.excalibur(other);
       
      }
      else if(playerMoveUsed.equals("Ragnarok"))
      {
        this.ragnarok(other);
      }
      if(other.health<=0)
      {
        return fighters;
        
      }
      ////////////////////////////////////////////////////////////////////////////////////////////////////
      //for the bot to attack
      if(otherMoveUsed.equals("Lunge"))
      {
        other.lunge(this);
      }
      else if(otherMoveUsed.equals("Bash"))
      {
        other.bash(this);
      }
      else if(otherMoveUsed.equals("Slash"))
      {
        other.slash(this);
      }
      else if(otherMoveUsed.equals("Stab"))
      {
        other.stab(this);
      }
      else if(otherMoveUsed.equals("Parry"))
      {
        other.parry(this);
      }
      else if(otherMoveUsed.equals("Charge"))
      {
        other.charge(this);
      }
      else if(otherMoveUsed.equals("Last Resort"))
      {
        other.lastResort(this);
      }
      else if(otherMoveUsed.equals("Rest"))
      {
        other.rest();
      }
      else if(otherMoveUsed.equals("Oblivion"))
      {
        other.oblivion(this);
      }
      else if(otherMoveUsed.equals("Starfall"))
      {
        other.starfall(this);
      }
      else if(otherMoveUsed.equals("Excalibur"))
      {
        other.excalibur(this);
      }
      else if(otherMoveUsed.equals("Ragnarok"))
      {
        other.ragnarok(this);
      }
      if(this.health<=0)
      {
        return fighters;
        
      }
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    //if the bot is faster than the user
    if(other.speed>=this.speed)
    {
      if(otherMoveUsed.equals("Lunge"))
      {
        other.lunge(this);
      }
      else if(otherMoveUsed.equals("Bash"))
      {
        other.bash(this);
      }
      else if(otherMoveUsed.equals("Slash"))
      {
        other.slash(this);
      }
      else if(otherMoveUsed.equals("Stab"))
      {
        other.stab(this);
      }
      else if(otherMoveUsed.equals("Parry"))
      {
        other.parry(this);
      }
      else if(otherMoveUsed.equals("Charge"))
      {
        other.charge(this);
      }
      else if(otherMoveUsed.equals("Last Resort"))
      {
        other.lastResort(this);
      }
      else if(otherMoveUsed.equals("Rest"))
      {
        other.rest();
      }
      else if(otherMoveUsed.equals("Oblivion"))
      {
        other.oblivion(this);
      }
      else if(otherMoveUsed.equals("Starfall"))
      {
        other.starfall(this);
      }
      else if(otherMoveUsed.equals("Excalibur"))
      {
        other.excalibur(this);
      }
      else if(otherMoveUsed.equals("Ragnarok"))
      {
        other.ragnarok(this);
      }
      if(this.health<=0)
      {
        return fighters;
        
      }
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
      //for the user to attack
      if(playerMoveUsed.equals("Lunge"))
      {
        this.lunge(other);
      }
      else if(playerMoveUsed.equals("Bash"))
      {
        this.bash(other);
      }
      else if(playerMoveUsed.equals("Slash"))
      {
        this.slash(other);
      }
      else if(playerMoveUsed.equals("Stab"))
      {
        this.stab(other);
      }
      else if(playerMoveUsed.equals("Parry"))
      {
        this.parry(other);
      }
      else if(playerMoveUsed.equals("Charge"))
      {
        this.charge(other);
      }
      else if(playerMoveUsed.equals("Last Resort"))
      {
        this.lastResort(other);
      }
      else if(playerMoveUsed.equals("Rest"))
      {
        this.rest();
      }
      else if(playerMoveUsed.equals("Oblivion"))
      {
        this.oblivion(other);
      }
      else if(playerMoveUsed.equals("Starfall"))
      {
        this.starfall(other);
      }
      else if(playerMoveUsed.equals("Excalibur"))
      {
        this.excalibur(other);
      }
      else if(playerMoveUsed.equals("Ragnarok"))
      {
        this.ragnarok(other);
      }
      if(other.health<=0)
      {
        return fighters;
        
      }
    }
    //for priority moves to return the characters stat back to normal
    if(playerLungeUsed)
    {
      this.lungeRecover();
    }
    if(playerBashUsed)
    {
      this.bashRecover();
    }
    if(otherLungeUsed)
    {
      other.lungeRecover();
    }
    if(otherBashUsed)
    {
      other.bashRecover();
    }
    
    return fighters;
    
  }
  public void randomOpponent(RPGCharacter player)
  {
    //to create a random opponent
    int a;
    Random r=new Random();
    name=nameGenerator();
    int level = player.level;
    int rSkillPoints=9+level-1;
    int rMaxHealth=1;
    int rAttack=1;
    int rDefence=1;
    int rSpeed=1;
    do{
      if(rSkillPoints>0)
      {
        a=r.nextInt(2);
        rMaxHealth=rMaxHealth+a;
        rSkillPoints=rSkillPoints-a;
      }
      if(rSkillPoints>0)
      {
        a=r.nextInt(2);
        rAttack=rAttack+a;
        rSkillPoints=rSkillPoints-a;
      }
      if(rSkillPoints>0)
      {
        a=r.nextInt(2);
        rDefence=rDefence+a;
        rSkillPoints=rSkillPoints-a;
      }
      if(rSkillPoints>0)
      {
        a=r.nextInt(2);
        rSpeed=rSpeed+a;
        rSkillPoints=rSkillPoints-a;
      }
    }while(rSkillPoints>0);
    
    maxHealth=rMaxHealth*40;
    health=maxHealth;
    attack=rAttack*40;
    defence=rDefence*40;
    speed=rSpeed*40;
    level=player.level;
    //to create movesets that challenge the user as they level up
    if(level==1)
    {
      moveSet[0]="Stab";
      moveSet[1]="blank";
      moveSet[2]="blank";
      moveSet[3]="blank";
    }
    else if(level>=2 && level<=5)
    {
      moveSet[0]="Stab";
    moveSet[1]="Lunge";
    moveSet[2]="Rest";
    moveSet[3]="blank";
  }
    else if(level>5)
    {
      moveSet[0]="Last Resort";
      moveSet[1]="Bash";
      moveSet[2]="Rest";
      moveSet[3]="Stab";
    }
  }
  //to create a random name
  public String nameGenerator()
  {
    String[] firstName = new String[59];
    String[] middleName= new String[3];
    String[] lastName = new String[60];
    //initializing first names
    firstName[0]="Jon";
    firstName[1]="Karl";
    firstName[2]="Moum";
    firstName[3]="Moton";
    firstName[4]="Urtragor";
    firstName[5]="Lumul";
    firstName[6]="Muul";
    firstName[7]="Immanuel";
    firstName[8]="Maximo";
    firstName[9]="Spencer";
    firstName[10]="Francisus";
    firstName[11]="Nader";
    firstName[12]="Rhaegar";
    firstName[13]="Captain Flint";
    firstName[14]="Mickey";
    firstName[15]="Cullen";
    firstName[16]="George";
    firstName[17]="Hannibal";
    firstName[18]="Geralt";
    firstName[19]="Mesmar";
    firstName[20]="Yorg";
    firstName[21]="Blintar";
    firstName[22]="Barlo";
    firstName[23]="Dennis";
    firstName[24]="Absin";
    firstName[25]="Cornelious";
    firstName[26]="Uhtred";
    firstName[27]="Fixikin";
    firstName[28]="Fiznard";
    firstName[29]="Soxtee";
    firstName[30]="Mytrarion";
    firstName[31]="Valtidion";
    firstName[32]="Zelvedion";
    firstName[31]="Thorand";
    firstName[32]="Varoll";
    firstName[33]="Haradill";
    firstName[34]="Tholaf";
    firstName[35]="Sverald";
    firstName[36]="Mika";
    firstName[37]="Gunmyr";
    firstName[38]="Nelly";
    firstName[39]="Dorgin";
    firstName[40]="Frehild";
    firstName[41]="Anenda";
    firstName[42]="Zali";
    firstName[43]="Bongun";
    firstName[44]="Cangolph";
    firstName[45]="Munom";
    firstName[46]="Mikobriar";
    firstName[47]="Radford";
    firstName[48]="Penrod";
    firstName[49]="Alan";
    firstName[50]="Eric";
    firstName[51]="Jake";
    firstName[52]="Barr";
    firstName[53]="Anne";
    firstName[54]="Louise";
    firstName[55]="Eleanor";
    firstName[56]="Val";
    firstName[57]="Terza";
    firstName[58]="Benita";
    
    //initializing middle name
    middleName[0]=" ";
    middleName[1]=" The ";
    middleName[2]=" of ";
    
    //initializing last names
    //if middle name is space
    lastName[0]="Sharpwood";
    lastName[1]="Sundream";
    lastName[2]="Snow";
    lastName[3]="Bisson";
    lastName[4]="Steelwatcher";
    lastName[5]="Burton";
    lastName[6]="Hildom";
    lastName[7]="Parris";
    lastName[8]="Son of Uhtred";
    lastName[9]="Gladstone";
    lastName[10]="Blackflare";
    lastName[11]="Firdown";
    lastName[12]="DarkGaze";
    lastName[13]="Bloodheart";
    lastName[14]="Redbringer";
    lastName[15]="Alais";
    lastName[16]="Mirthreaver";
    lastName[17]="Aerneros";
    lastName[18]="Smith";
    lastName[19]="Naranyon";
    //if middle name is the
    lastName[20]="Destroyer of Worlds";
    lastName[21]="Cruel";
    lastName[22]="Careless";
    lastName[23]="Feared";
    lastName[24]="KingSlayer";
    lastName[25]="Champion";
    lastName[26]="DarkKing";
    lastName[27]="Hungry";
    lastName[28]="Toothless";
    lastName[29]="Giant";
    lastName[30]="Dizzy";
    lastName[31]="Unsightly";
    lastName[32]="Thoughtful";
    lastName[33]="Forgiving";
    lastName[34]="Barbarous";
    lastName[35]="Chunky";
    lastName[36]="Fair";
    lastName[37]="Grieving";
    lastName[38]="Bringer of the Apocolypse";
    lastName[39]="Horrible";
    //if middle name is of
    lastName[40]="the North";
    lastName[41]="the South";
    lastName[42]="the East";
    lastName[43]="the West";
    lastName[44]="Rivia";
    lastName[45]="Clickwick";
    lastName[46]="Denmark";
    lastName[47]="the Ranger";
    lastName[48]="Mibrun";
    lastName[49]="the Crown";
    lastName[50]="Cherryville";
    lastName[51]="the Future";
    lastName[52]="the Past";
    lastName[53]="the Walrus";
    lastName[54]="Fouriver";
    lastName[55]="the Subground";
    lastName[56]="the Sky";
    lastName[57]="the Kingsguard";
    lastName[58]="the Warriors";
    lastName[59]="the Vikings";
    
    Random r=new Random();
    int a = r.nextInt(59);
    int b = r.nextInt(3);
    int c = r.nextInt(20);
    if(b==1)
    {
      c=c+20;
    }
    else if(b==2)
    {
      c=c+40;
    }
    else
    {
      
    }
    String randomName=firstName[a]+middleName[b]+lastName[c];
    return randomName;
  }
}