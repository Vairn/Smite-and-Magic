Include "spellsold.bb"

Global  Dlay=100
Type clerical
Field ID
Field Level
Field Spell
Field Name$
Field Effect
Field Damage[2]
Field Cost[2]
Field Desc$[10]
Field combat
End Type

Type Sorceror
Field ID
Field Level
Field Spell
Field Name$
Field Effect ; 0 is all
Field Damage[2]
Field Cost[2]
Field Desc$[10]
Field combat
End Type

Type party
	Field Char[8]
	Field light=0
	Field protmag=0
	Field protelm=0
	Field shield=0
	Field shelter=0
	Field wizard=0
	Field eagle=0
	Field Floating=0
	Field invis=0
	Field dambonus=0
End Type
party.party=New party
Type character
	Field name$
	Field race,class,sex,allignment
	Field MGT,INTE,PER,ENDU,SPD,ACC,LCK
	

	Field HP,MHP
	Field SP,MSP
	Field AC,THV
	Field backpack[6]
	Field equiped[6]
	Field backplus[6]
	Field equiplus[6]
	Field backefft[6]
	Field equiefft[6]
	Field GOLD
	Field Exper
	Field GEMS
	Field FOOD
	Field Skill[2]
	Field SPLVL
	Field Spell[48]
	Field ID
	Field age
	Field Town
	Field LVL
	Field day
	Field plus
	Field entity
	Field battle[9]
	Field asleep
	Field dead
	Field erad
	Field posion
	Field disease
	Field stones
	Field silence
	Field paralysed
	Field tMGT,tINTE,tPER,tENDU,tSPD,tACC,tLCK ; temp effects
	Field tHP,tMHP,tSP,tMSP
End Type


Type hireling
	Field name$
	Field race,class,sex,allignment
	Field MGT,INTE,PER,ENDU,SPD,ACC,LCK
	Field HP,MHP
	Field SP,MSP
	Field AC,THV
	Field backpack[6]
	Field equiped[6]
	Field backplus[6]
	Field equiplus[6]
	Field backefft[6]
	Field equiefft[6]
	Field Cost
	Field Exper
	Field GEMS
	Field FOOD
	Field Skill[2]
	Field SPLVL
	Field Spell[48]
	Field ID
	Field age
	Field Town
	Field LVL
	Field day
	Field plus
	Field entity
	Field asleep
	Field dead
	Field erad
	Field posion
	Field disease
	Field stones
	Field paralysed
Field tMGT,tINTE,tPER,tENDU,tSPD,tACC,tLCK
	Field tHP,tMHP,tSP,tMSP
End Type
Dim race$(4),class$(7),town$(5),skill$(16)
race(0)="Human"
race(1)="Elf"
race(2)="Dward"
race(3)="Gnome"
race(4)="Half-Orc"
class(0)="Knight"
class(1)="Paladin"
class(2)="Archer"
class(3)="Cleric"
class(4)="Sorceror"
class(5)="Robber"
class(6)="Ninja"
class(7)="Barbarian"
town(0)="NONE"
town(1)="Middlegate"
town(2)="Atlantium"
town(3)="Tundra"
town(4)="Vulcania"
town(5)="Sandsobar"

skill(0)="............"
skill(1)="Arms Master"
skill(2)="Athlete"
skill(3)="Cartographer"
skill(4)="Crusader"
skill(5)="Diplomat"
skill(6)="Gambler"
skill(7)="Gladiator"
skill(8)="Hero/Heroine"
skill(9)="Linguist"
skill(10)="Merchant"
skill(11)="Mountaineer"
skill(12)="Navigator"
skill(13)="Pathfinder"
skill(14)="PickPocket"
skill(15)="Soldier"
Dim nxoffset(8),nyoffset(8)

nxoffset(1)=0: nyoffset(1)=0
nxoffset(2)=0: nyoffset(2)=1
nxoffset(3)=1: nyoffset(3)=0
nxoffset(4)=1: nyoffset(4)=1
nxoffset(5)=2: nyoffset(5)=0
nxoffset(6)=2: nyoffset(6)=1
nxoffset(7)=3: nyoffset(7)=0
nxoffset(8)=3: nyoffset(8)=1


Global mon_no=0
Type monster
	Field id
	Field name$
	Field HP,ohp
	Field SP
	Field AC
	Field Pabil,SAbil,Oabil
	Field Abil1
	Field Abil2
	Field Exper,oexp
	Field Gold
	Field gems
	Field MRes
	Field damage
	Field Picture
	Field Undead
	Field Archer
	Field flee
	Field speed
	;temp
	Field Colour
End Type

Global ABLE=0
Dim tempa(8)
Dim tempb(8)
Dim tempc(8)
Dim adesc$(32)
Dim partydec$(32)
Dim ent_battle(9)
Global amount
f=ReadFile("adesc.log")
For a=0 To 31
	adesc$(a)=ReadLine(f)
	adec$=adec+adesc$(a)+"#"
Next
f=ReadFile("partyeffect.log")
For a=0 To 31
	partydec$(a)=ReadLine(f)
	pdec$=pdec+partydec$(a)+"#"
Next
pdec$=pdec+"NONE#"
Type battlemonst
	Field id,realID
	Field name$
	Field HP,ohp
	Field SP
	Field AC
	Field Pabil,SAbil,Oabil
	Field Abil1
	Field Abil2
	Field Exper,oexp
	Field Gold
	Field gems
	Field MRes
	Field damage
	Field Picture
	Field Undead
	Field Archer
	Field flee
	Field speed
	Field amount=1
	Field entity
	Field posX,posY
	Field status$
	Field asleep
	Field dead
	Field erad
	Field posion
	Field disease
	Field stones
	Field paralysed
	Field silence
	Field Fireencased=0
	Field waterencased=0
	Field Earthencased=0
	Field airencased=0

End Type

Type item
	Field name$
	Field Value
	Field Damage
	Field ID
	Field Usableby
	Field usa[8]
	Field effect$[2]; 0 is type 1 amount
	Field bonus$[2] ; 0 is type 1 amount
End Type

CloseFile(f)
;Graphics3D 1024,768,32,1
Graphics3D 800,600,32,2
SetBuffer BackBuffer()
;Graphics3D 640,480,32,2
grass_tex=LoadTexture( "Textures\pave03.jpg")

Global monbox=LoadImage("monstbox.bmp")
	Global charbox=LoadImage("charbox.bmp")
	Global txtbox=LoadImage("txtbox.bmp")
	Global splbox=LoadImage("spellbox.bmp")
	Select GraphicsWidth()
	Case 1024
		sc#=1.28
;		ScaleImage monbox,sc,sc
	ScaleImage charbox,sc,sc
	Case 800
		sc#=1
	Case 640
		sc#=.8
		ScaleImage monbox,sc,sc
	ScaleImage charbox,sc,sc
End Select
; Might and Magic 2 - Battle engine.
; this is going to be the original battle engine form mm2 with some little changes...
; ie: and isometric view of the battle field. :) but every thing else is the same.

Dim monst(14),posx(12),posz(12),charx(8),charz(8)

posx(2)=10	: posz(2)=10
posx(1)=20	: posz(1)=10
posx(3)=30	: posz(3)=10
posx(5)=10	: posz(5)=20
posx(4)=20	: posz(4)=20
posx(6)=30	: posz(6)=20
posx(8)=10	: posz(8)=30
posx(7)=20	: posz(7)=30
posx(9)=30	: posz(9)=30
posx(10)=20	: posz(10)=40
posx(11)=1100	: posz(11)=1100

charx(2)=10	: charz(2)=-10
charx(1)=20	: charz(1)=-10
charx(3)=30	: charz(3)=-10
charx(5)=10	: charz(5)=-20
charx(4)=20	: charz(4)=-20
charx(6)=30	: charz(6)=-20
charx(7)=15	: charz(7)=-30
charx(8)=25	: charz(8)=-30
Dim ORDER(32)
Global EXPgained
init()
; might not be needed in it when incorperated to the main game.
	Global camera=CreateCamera()
;	CameraViewport camera,0,0,GraphicsWidth()-200,GraphicsHeight()-100
PositionEntity camera,10,25,-30
RotateEntity camera,40,-30,0
	CameraRange camera,1,1000
	light=CreateLight()
	RotateEntity light,0,0,0
	ground=CreatePlane()
	EntityTexture ground,grass_tex
Type ORDER
Field posistion
Field what
Field ID
Field name$
End Type
Dim txt(9)
;Global menu=LoadSprite("bmenu.bmp",4)
;ScaleSprite menu,5,5
;SpriteViewMode menu,1
;txt(1)=LoadTexture("Models\batttle\Attack.bmp")
;txt(2)=LoadTexture("Models\batttle\fight.bmp")
;txt(3)=LoadTexture("Models\batttle\shoot.bmp")
;txt(4)=LoadTexture("Models\batttle\cast.bmp")
;txt(5)=LoadTexture("Models\batttle\use.bmp")
;txt(6)=LoadTexture("Models\batttle\block.bmp")
;txt(7)=LoadTexture("Models\batttle\run.bmp")
;txt(8)=LoadTexture("Models\batttle\exchange.bmp")
;txt(9)=LoadTexture("Models\batttle\view.bmp")

;ent_battle(0)=LoadMesh("models\batttle\ring.3ds")
Dim bx(8)
Dim by(8)
Dim bz(8)
Dim rx(8)
Dim ry(8)
Dim rz(8)

bx(1)=19	:	by(1)=11	:	bz(1)=-12
bx(2)=10	:	by(2)=11	:	bz(2)=-12
bx(3)=28	:	by(3)=11	:	bz(3)=-12
bx(4)=19	:	by(4)=11	:	bz(4)=-21
bx(5)=10	:	by(5)=11	:	bz(5)=-21
bx(6)=28	:	by(6)=11	:	bz(6)=-21

rx(1)=39	:	ry(1)=-29	:	rz(1)=0
rx(2)=42	:	ry(2)=-3	:	rz(2)=-17
rx(3)=30	:	ry(3)=-47	:	rz(3)=11
rx(4)=46	:	ry(4)=-54	:	rz(4)=16
rx(5)=58	:	ry(5)=-11	:	rz(5)=-15
rx(6)=33	:	ry(6)=-61	:	rz(6)=17

;bx(7)=	:	by(7)=	:	bz(7)=
;bx(8)=	:	by(8)=	:	bz(8)=
;ScaleEntity ent_battle(0),.05,.05,.05

;PositionEntity ent_battle(0),10,10,-10


Global battle=True
Dim aaa$(255)
;beta_Text()
SetBuffer BackBuffer()
mainbattloop
Function mainbattloop()


While battle=True
hmny=0
alive=1
UpdateWorld
RenderWorld
encasement()
For bmon.battlemonst=Each battlemonst
	If bmon\hp<=0
		UPDATEMONSTERS(bmon\ID)
EndIf
Next


	dc=0
dead=0
For a=255 To 0 Step -1

For bmon.battlemonst=Each battlemonst
	
	hmny=hmny+1

		If bmon\speed=a
;			aaa(a)=aaa(a)+bmon\name$+","+bmon\id+" "
		menu(0,bmon\id)
	End If
	
	If hmny=0 Exit
Next
;	For bmon.battlemonst=Each battlemonst
;	hmny=hmny+1
;	Next
	If hmny=0
	 battle=False
	Exit
	EndIf

For char.character=Each character
	If char\town>0
		If char\spd=a
			aaa(a)=aaa(a)+char\name$+","+char\id+" "
		If char\hp>0
			menu(1,char\id)	
		Else 
		dead=dead+1	; since character is dead this will increase. 
		EntityAlpha char\entity,.1
		End If
		
		EndIf
		EndIf
		For bmon.battlemonst=Each battlemonst
	If bmon\hp<=0
		UPDATEMONSTERS(bmon\ID)
EndIf
Next
;showtext
;Flip
;VWait


Next
;DebugLog "alive:"+dead
	If hmny=0 battle=False
	If dead=6 battle=False
	Next
showtext()
VWait
Flip
Wend
;debuglog "BATTLEWON"
eobps=6
DebugLog "Each party Member recives "+(expgained/eobps)+" Experiance Points"
;DebugLog "alive:"+alive

End

End Function
Function init()
SeedRnd MilliSecs()
able=Rnd(5)+1

; these lines are in here until it is added to the main program...
	load_item("items.dat")
	load_monster("monsters.dat")
	load_roster("roster.dat")
	create_clerical()
	create_sorceror()
; choose a random amount of monsters
;	mon_no=(Rnd(5)+5)
	mon_no=11
	id=1
	Repeat	
	;debuglog mon_no
		
		monster=Rnd(231)+1
;		monster=253-Rnd(32)
		hmny=Rnd(2)+1
		;debuglog hmny
		
		For a=1 To hmny

		If id=11  amount=Rnd(250)+5
		;If id=11  amount=5

		For mon.monster=Each monster
			If mon\id=monster
			bmon.battlemonst= New battlemonst
			bmon\ID=id ; rank in the battle.
			id=id+1
;			DebugLog "ID:"+id
			bmon\realID=mon\ID
			bmon\name$=mon\name$
			bmon\HP=mon\hp
			bmon\OHP=mon\hp
			bmon\SP=mon\sp
			bmon\AC=mon\ac
			bmon\pabil=mon\pabil
			bmon\oabil=mon\oabil
			bmon\sabil=mon\sabil			
			bmon\Abil1=mon\abil1
			bmon\Abil2=mon\abil2
			bmon\Exper=mon\exper
			bmon\Gold=mon\gold
			bmon\MRes=mon\mres
			bmon\damage=mon\damage
			bmon\Picture=mon\picture
			bmon\Undead=mon\undead
			bmon\Archer=mon\archer
			bmon\flee=mon\flee
			bmon\speed=mon\speed
			bmon\amount=amount
			; when there are 3d models it will load it in for now, it is a cube coloured depending on the monster.
			bmon\entity=CreateCube()
;			txt_atck=LoadTexture("D:\Adams\Blitz3d\mm2\Models\batttle\cast.bmp")
;			EntityTexture bmon\entity,txt_atck
			If mon\Colour=0	EntityColor bmon\entity,mon\ID,0,0
			If mon\Colour=1	EntityColor bmon\entity,0,mon\ID,0
			If mon\Colour=2	EntityColor bmon\entity,0,0,mon\ID
			PositionEntity bmon\entity,posx(bmon\id),10,posz(bmon\id)			
			EndIf
			If id>mon_no Exit
		Next
	Next
	If id>mon_no Exit

	Forever
;debuglog "THESE ARE THE CHOOSEN ONES"	
	For bmon.battlemonst=Each battlemonst
	If bmon\id=11
		Print bmon\ID+":"+bmon\name$+"("+bmon\amount+")"
	Else
		Print bmon\ID+":"+bmon\name$
	EndIf
	
	;debuglog bmon\ID+":"
	;debuglog bmon\name$+" HP:"+(bmon\hp)+" Exp:"+bmon\exper+" gold:"+bmon\gold+" Gems:"+bmon\gems+" MAX damage: "+bMon\damage
	;debuglog "Undead: "+bmon\undead+" Archer: "+bmon\Archer+" Can't Run: "+bmon\flee+" Picture:"+bmon\picture+" AC:"+bmon\ac+" SP: "+bmon\sp+" MRES:"+bmon\mres
	;debuglog "single ability "+bmon\abil1+":"+adesc$(bmon\abil1)
;	If Mid(Right(Hex(bmon\pabil),2),1,1)>0 ;debuglog "Party  ability "+bmon\abil2+":"+partydec$(bmon\abil2)

	Next
	For a=1 To 6 ; will be partysize and characters have a party flag in fully finished version
		For char.character=Each character
			If char\id=a
				char\entity=CreateSphere(16)
;				char\entity=CreateCube()
				PositionEntity char\entity,charx(a),10,charz(a)
				EntityColor char\entity,(char\mgt)*10,char\per*10,char\spd*10
				If char\id<=able
					char\battle[1]=1
					char\battle[2]=1
					char\battle[3]=0
					If char\class=2
						;bow=item_check(3) ; Does the character have a bow?
						;If bow=1
							char\battle[3]=1
						;EndIf
					EndIf
				Else
					char\battle[1]=0
					char\battle[2]=0
					;bow=item_check(3) ; Does the character have a bow?
					;If bow=1  (a cleric can't equip a bow, this protects against hacking.
						char\battle[3]=1
					;EndIf
				End If	
				If char\class=3 
					char\battle[3]=0
				EndIf
				If char\class=1 Or char\class=3
					char\battle[4]=1 ; clerical
				EndIf
				If char\class=2 Or char\class=4
					char\battle[4]=1 ; sorcery
				EndIf
				For rst=5 To 9
					char\battle[rst]=1
				Next
				
				
			End If
		Next
	Next
	
	
		
End Function

;

Function load_item(file$)
id=0
filein=ReadFile(file$)
For a=0 To 255
item.item = New item
item\id=a
For c=0 To 11
ch$=Chr$(ReadByte(filein))
item\name$=item\name$+ch$
Next
ReadByte(filein)
as$=as$+a+": "+item\name$+"#"
item\usableby=ReadByte(filein)
temp$=Hex(ReadByte(filein))
item\bonus[0]=hex2int(Mid(temp$,7,1))
item\bonus[1]=hex2int(Right(temp$,1))
temp$=Hex(ReadByte(filein))
item\effect[0]=hex2int(Mid(temp$,7,1))
item\effect[1]=hex2int(Right(temp$,1))
item\damage=ReadByte(filein)
ReadByte(filein)
item\value=ReadShort(filein)
Next
CloseFile(filein)
End Function

Function load_monster(file$)
fin=ReadFile(file$)
For a=0 To 255

	mon.monster=New monster
	mon\id=a
	
	For n=0 To 13
	temp=ReadByte(fin)
	ch$=Chr(temp-128)
	If ch$<>" "	mon\name$=mon\name$+ch$
	Next 
	;;debuglog	mon\ID+":"+mon\name$
	;hit points, 
	temp=ReadByte(fin)
	mon\ohp=temp

	If temp<71
		mon\hp=temp+1
	Else If temp>70 And temp<134
		mon\hp=70+((temp-70)*10)
	Else If temp>133 And temp<178
		mon\hp=700+((temp-134)*100)
	Else If temp>177 
			mon\hp=64000-(255-temp)*1000
	End If
	
	; Experience similar in workings to hp but able to use bigger values.
	tempE=ReadByte(fin)
	mon\oexp=tempe
	If tempe<62
	mon\exper=60+((tempe-37)*10)
	Else If tempe<128
	mon\exper=200+((tempe-65)*100)
	Else If tempe<138
	mon\exper=1000+((tempe-128)*1000)
	Else If tempe<160
	mon\exper=10000+((tempe-137)*1000)
	Else If tempe<190
	mon\exper=10000+((tempe-160)*10000)
	Else If tempe<220
	mon\exper=0000+((tempe-191)*10000)
	Else
	mon\exper=10000+((tempe-224)*10000)

	EndIf
	; Gold.. gems.. unknown how it works atm, I think the forst hex character is gold and the second is gems.
	; experience also debates what item is given i think.
	gold=ReadByte(fin)
	;mon\gold=hex2int(Mid(Right(Hex(gold),2),1,1))
	;mon\Gems=hex2int(Right(Hex(gold),1))
	mon\Pabil=ReadByte(fin)
	mon\Sabil=ReadByte(fin)
	mon\Oabil=ReadByte(fin)
	mon\speed=ReadByte(fin)
	mon\picture=ReadByte(fin)
	mon\AC=ReadByte(fin)
	mon\damage=ReadByte(fin)
	mon\Speed=ReadByte(fin)
	mon\MRES=ReadByte(fin)
	

	For ab=1 To 8
	tempa(ab-1)=Mid(Right(Bin(mon\Sabil),8),ab,1)
	tempb(ab-1)=Mid(Right(Bin(mon\Pabil),8),ab,1)
	tempc(ab-1)=Mid(Right(Bin(mon\oAbil),8),ab,1)

	Next
	mon\undead=tempa(0)
	mon\archer=tempa(1)
	mon\Flee=tempb(1)
	tempo=0
	If tempa(7)=1	tempo=tempo+1
	If tempa(6)=1	tempo=tempo+2
	If tempa(5)=1	tempo=tempo+4
	If tempa(4)=1	tempo=tempo+8
	If tempa(3)=1	tempo=tempo+16
	mon\abil1=tempo
	tempo=0
	If tempb(7)=1	tempo=tempo+1
	If tempb(6)=1	tempo=tempo+2
	If tempb(5)=1	tempo=tempo+4
	If tempb(4)=1	tempo=tempo+8
	If tempb(3)=1	tempo=tempo+16
	mon\abil2=tempo
	;debuglog mon\ID+":"
	;debuglog mon\name$+" HP:"+(mon\hp)+"("+temp+") Exp:"+mon\exper+"("+tempe+") gold:"+Mid(Right(Hex(mon\gold),2),1,1)+" Gems:"+Right(Hex(mon\gold),1)+" MAX damage: "+Mon\damage
	;debuglog "Undead: "+mon\undead+" Archer: "+mon\Archer+" Can't Run: "+mon\flee+" Picture:"+mon\picture+" AC:"+mon\ac+" SP: "+mon\sp+" MRES:"+mon\mres
	;debuglog "single ability "+mon\abil1+":"+adesc$(mon\abil1)
;	If Mid(Right(Hex(mon\pabil),2),1,1)>0 ;debuglog "Party  ability "+mon\abil2+":"+partydec$(mon\abil2)

	mon\Colour=clr
	clr=clr+1
	If clr=3
	clr=0
	EndIf
		
Next
CloseFile(fin)
End Function
Function hex2int(String$)
	Select String$
	Case "0"
	temp=0
	Case "1"
	temp=1
	Case "2"
		temp=2
	Case "3"
		temp=3
	Case "4"
		temp=4
	Case "5"
		temp=5
	Case "6"
		temp=6
	Case "7"
		temp=7
	Case "8"
		temp=8
	Case "9"
		temp=9
	Case "A"
		temp=10
	Case "B"
		temp=11
	Case "C"
		temp=12
	Case "D"
		temp=13
	Case "E"
		temp=14
	Case "F"
		temp=15
End Select
Return temp
End Function


Function load_roster(file$)
fin=ReadFile(file$)
For a=1 To 24
	;;debuglog "character:"+a
	char.character=New character
	char\ID=a
	For c=0 To 9
		ch$=Chr(ReadByte(fin))
		char\name$=char\name$+ch
	Next
	
	;;debuglog "NAME:"+char\name$
	ReadByte(fin)
	char\town=ReadByte(fin)
	
	;;debuglog "Town: "+town(char\town)
	char\sex=ReadByte(fin)
	If char\sex=0
	;;debuglog "sex: Male"
	Else
	;;debuglog "sex: female"
	End If
	char\allignment=ReadByte(fin)

	Select 	char\allignment

	Case 0
	;;debuglog "Allignment: Good"
	Case 1
	;;debuglog "Allignment: Neutral"
	Case 2
		;;debuglog "Allignment: Evil"
	End Select
	char\race=ReadByte(fin)
	;;debuglog "Race: "+race(char\race)
	char\class=ReadByte(fin)
	;;debuglog "Class: "+class(char\class)
	; Statistics

	char\MGT=ReadByte(fin)	
	char\INTE=ReadByte(fin)	
	char\PER=ReadByte(fin)
	crloc=FilePos(fin)
	SeekFile(fin,(((a-1)*130)+39))
	char\ENDU=ReadByte(fin)	
	SeekFile(fin,crloc)	
	char\SPD=ReadByte(fin)	
	char\ACC=ReadByte(fin)	
	char\LCK=ReadByte(fin)	
		
	;;debuglog "Might:"+char\mgt
		;;debuglog "Intellect:"+char\inte
		;;debuglog "Personality:"+char\per
		;;debuglog "Endurance:"+char\endu
		;;debuglog "Speed:"+char\spd
		;;debuglog "Accuracy:"+char\acc
		;;debuglog "Luck:"+char\lck
		
		SeekFile(fin,(((a-1)*130)+30))
		char\thv=ReadByte(fin)
		;;debuglog "Thievery: "+char\thv
		char\lvl=ReadByte(fin)
		char\lvl=ReadByte(fin)
		;;debuglog "Level: "+char\lvl
		char\age=ReadByte(fin)
		;;debuglog "Age: "+char\age
		char\day=ReadByte(fin)
		;;debuglog "Days old: "+char\day
		char\sPLVL=ReadByte(fin)
		;;debuglog "Spell Level:"+char\splvl
		char\food=ReadByte(fin)
		char\food=ReadByte(fin)
		;;debuglog "Food:"+char\food
		ReadByte(fin)	
		ReadByte(fin)	
		For eq=0 To 5
			char\equiped[eq]=ReadByte(fin)		
		Next
		For eq=0 To 5
			char\equiplus[eq]=ReadByte(fin)		
		Next
		For eq=0 To 5
			char\equiefft[eq]=ReadByte(fin)		
		Next
	For eq=0 To 5
		For item.item=Each item
			If char\equiped[eq]=item\id
				;;debuglog "Equiped:"+item\name$+"+"+char\equiplus[eq]
			End If
		Next
	Next
			For eq=0 To 5
			char\backpack[eq]=ReadByte(fin)		
		Next
		For eq=0 To 5
			char\backplus[eq]=ReadByte(fin)		
		Next
		For eq=0 To 5
			char\backefft[eq]=ReadByte(fin)		
		Next
	For eq=0 To 5
		For item.item=Each item
			If char\backpack[eq]=item\id
				;;debuglog "Backpack:"+item\name$+"+"+char\backplus[eq]
			End If
		Next
	Next
		SeekFile(fin,((a-1)*130)+80)
		temp$=Hex(ReadByte(fin))
		
		char\skill[0]=hex2int(Mid(Right(temp$,2),1,1))
		char\skill[1]=hex2int(Right(temp$,1))

		;;debuglog "Skill 1: "+skill(char\skill[0])
		;;debuglog "Skill 2: "+skill(char\skill[1])
		stemp1$=Bin(ReadByte(fin))
		stemp2$=Bin(ReadByte(fin))
		stemp3$=Bin(ReadByte(fin))
		stemp4$=Bin(ReadByte(fin))
		stemp5$=Bin(ReadByte(fin))
		stemp6$=Bin(ReadByte(fin))
stemp1=Right(stemp1,8)
stemp2=Right(stemp2,8)
stemp3=Right(stemp3,8)
stemp4=Right(stemp4,8)
stemp5=Right(stemp5,8)
stemp6=Right(stemp6,8)
spl2=0
		For spl=8 To 1 Step -1
			char\spell[spl2]=Int(Mid(stemp1,spl,1))
			spl2=spl2+1
		Next
		For spl=16 To 9 Step -1
			char\spell[spl2]=Int(Mid(stemp2,spl-8,1))
			spl2=spl2+1
		Next
		For spl=24 To 17 Step -1
			char\spell[spl2]=Int(Mid(stemp3,spl-16,1))
						spl2=spl2+1
		Next
		For spl=32 To 25 Step -1
			char\spell[spl2]=Int(Mid(stemp4,spl-24,1))
						spl2=spl2+1
		Next
		For spl=40 To 33 Step -1
			char\spell[spl2]=Int(Mid(stemp5,spl-32,1))
						spl2=spl2+1
		Next
		For spl=48 To 41 Step -1
			char\spell[spl2]=Int(Mid(stemp6,spl-40,1))
						spl2=spl2+1
		Next
		spl2=0
		;;debuglog "Has Spells"
		as$=""
		For spl=1 To 7

		as$=as$+"1-"+Str$(spl)+"(
		as$=as$+char\spell[spl2]+") "
		spl2=spl2+1
		Next
		;;debuglog as$
	as$=""

		For spl=1 To 7
		as$=as$+"2-"+Str$(spl)+"(
		as$=as$+char\spell[spl2]+") "
		spl2=spl2+1
	
		Next
			;;debuglog as$	
	as$=""

		For spl=1 To 6
			as$=as$+"3-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
			
				
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 6
			as$=as$+"4-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 5
			as$=as$+"5-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 5
			as$=as$+"6-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"7-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"8-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"9-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
		Next
				;;debuglog as$
		SeekFile(fin,((a-1)*130)+88)
		char\SP=ReadShort(fin)
		char\MSP=ReadShort(fin)
		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		char\GEMS=temp1+(temp2*256)
		char\HP=ReadShort(fin)
		char\MHP=ReadShort(fin)
		;;debuglog "HIT POINTS "+char\Hp+"/"+char\MHp
		;;debuglog "SPELL POINTS "+char\sp+"/"+char\Msp

		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
		temp4=ReadByte(fin)
			char\exper=temp1+(temp2*256)+(temp3*65536)+(temp4*16777216)
temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
			char\GOLD=temp1+(temp2*256)+(temp3*65536)

;		;;debuglog Right(Hex(temp1),2)+" "+Right(Hex(temp2),2)+" "+Right(Hex(temp3),2)+" "+Right(Hex(temp4),2)+" "
		;;debuglog "Experience:"+char\exper
	;;debuglog "Gold:"+char\gold		
		;;debuglog "Gems:"+char\gems
		SeekFile(fin,((a-1)*130)+121)
		char\plus=ReadByte(fin)
		;;debuglog "Has Plus:"+Right(Bin(char\plus),8)
		SeekFile(fin,((a)*130))
;	For b=0 To 107
;	temp=ReadByte(fin)
;	;;debuglog 	Chr(temp)
;	Next
	;;debuglog "End Of character:"+a
	;;debuglog ""

Next

For a=1 To 24
	;;debuglog "Hireling:"+a
	hire.hireling=New hireling
	hire\ID=a
	For c=0 To 9
		ch$=Chr(ReadByte(fin))
		hire\name$=hire\name$+ch
	Next
	
	;;debuglog "NAME:"+hire\name$
	ReadByte(fin)
	hire\town=ReadByte(fin)
	
	;;debuglog "Town: "+town(hire\town)
	hire\sex=ReadByte(fin)
	If hire\sex=0
	;;debuglog "sex: Male"
	Else
	;;debuglog "sex: female"
	End If
	hire\allignment=ReadByte(fin)

	Select 	hire\allignment

	Case 0
	;;debuglog "Allignment: Good"
	Case 1
	;;debuglog "Allignment: Neutral"
	Case 2
		;;debuglog "Allignment: Evil"
	End Select
	hire\race=ReadByte(fin)
	;;debuglog "Race: "+race(hire\race)
	hire\class=ReadByte(fin)
	;;debuglog "Class: "+class(hire\class)
	; Statistics

	hire\MGT=ReadByte(fin)	
	hire\INTE=ReadByte(fin)	
	hire\PER=ReadByte(fin)
	crloc=FilePos(fin)
	SeekFile(fin,((((a+24)-1)*130)+39))
	hire\ENDU=ReadByte(fin)	
	SeekFile(fin,crloc)	
	hire\SPD=ReadByte(fin)	
	hire\ACC=ReadByte(fin)	
	hire\LCK=ReadByte(fin)	
		
	;;debuglog "Might:"+hire\mgt
		;;debuglog "Intellect:"+hire\inte
		;;debuglog "Personality:"+hire\per
		;;debuglog "Endurance:"+hire\endu
		;;debuglog "Speed:"+hire\spd
		;;debuglog "Accuracy:"+hire\acc
		;;debuglog "Luck:"+hire\lck
		
		SeekFile(fin,((((a+24)-1)*130)+30))
		hire\thv=ReadByte(fin)
		;;debuglog "Thievery: "+hire\thv
		hire\lvl=ReadByte(fin)
		hire\lvl=ReadByte(fin)
		;;debuglog "Level: "+hire\lvl
		hire\age=ReadByte(fin)
		;;debuglog "Age: "+hire\age
		hire\day=ReadByte(fin)
		;;debuglog "Days old: "+hire\day
		hire\sPLVL=ReadByte(fin)
		;;debuglog "Spell Level:"+hire\splvl
		hire\food=ReadByte(fin)
		hire\food=ReadByte(fin)
		;;debuglog "Food:"+hire\food
		ReadByte(fin)	
		ReadByte(fin)	
		For eq=0 To 5
			hire\equiped[eq]=ReadByte(fin)		
		Next
		For eq=0 To 5
			hire\equiplus[eq]=ReadByte(fin)		
		Next
		For eq=0 To 5
			hire\equiefft[eq]=ReadByte(fin)		
		Next
	For eq=0 To 5
		For item.item=Each item
			If hire\equiped[eq]=item\id
				;;debuglog "Equiped:"+item\name$+"+"+hire\equiplus[eq]
			End If
		Next
	Next
			For eq=0 To 5
			hire\backpack[eq]=ReadByte(fin)		
		Next
		For eq=0 To 5
			hire\backplus[eq]=ReadByte(fin)		
		Next
		For eq=0 To 5
			hire\backefft[eq]=ReadByte(fin)		
		Next
	For eq=0 To 5
		For item.item=Each item
			If hire\backpack[eq]=item\id
				;;debuglog "Backpack:"+item\name$+"+"+hire\backplus[eq]
			End If
		Next
	Next
		SeekFile(fin,(((a+24)-1)*130)+80)
		temp$=Hex(ReadByte(fin))
		
		hire\skill[0]=hex2int(Mid(Right(temp$,2),1,1))
		hire\skill[1]=hex2int(Right(temp$,1))
		
				;;debuglog "OFFSET:"+FilePos(fin)
		;;debuglog "Skill 1: "+skill(hire\skill[0])
		;;debuglog "Skill 2: "+skill(hire\skill[1])
		stemp1$=Bin(ReadByte(fin))
		stemp2$=Bin(ReadByte(fin))
		stemp3$=Bin(ReadByte(fin))
		stemp4$=Bin(ReadByte(fin))
		stemp5$=Bin(ReadByte(fin))
		stemp6$=Bin(ReadByte(fin))
stemp1=Right(stemp1,8)
stemp2=Right(stemp2,8)
stemp3=Right(stemp3,8)
stemp4=Right(stemp4,8)
stemp5=Right(stemp5,8)
stemp6=Right(stemp6,8)
spl2=0
		For spl=8 To 1 Step -1
			hire\spell[spl2]=Int(Mid(stemp1,spl,1))
			spl2=spl2+1
		Next
		For spl=16 To 9 Step -1
			hire\spell[spl2]=Int(Mid(stemp2,spl-8,1))
			spl2=spl2+1
		Next
		For spl=24 To 17 Step -1
			hire\spell[spl2]=Int(Mid(stemp3,spl-16,1))
						spl2=spl2+1
		Next
		For spl=32 To 25 Step -1
			hire\spell[spl2]=Int(Mid(stemp4,spl-24,1))
						spl2=spl2+1
		Next
		For spl=40 To 33 Step -1
			hire\spell[spl2]=Int(Mid(stemp5,spl-32,1))
						spl2=spl2+1
		Next
		For spl=48 To 41 Step -1
			hire\spell[spl2]=Int(Mid(stemp6,spl-40,1))
						spl2=spl2+1
		Next
		spl2=0
		;;debuglog "Has Spells"
		as$=""
		For spl=1 To 7

		as$=as$+"1-"+Str$(spl)+"(
		as$=as$+hire\spell[spl2]+") "
		spl2=spl2+1
		Next
		;;debuglog as$
	as$=""

		For spl=1 To 7
		as$=as$+"2-"+Str$(spl)+"(
		as$=as$+hire\spell[spl2]+") "
		spl2=spl2+1
	
		Next
			;;debuglog as$	
	as$=""

		For spl=1 To 6
			as$=as$+"3-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
				
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 6
			as$=as$+"4-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 5
			as$=as$+"5-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 5
			as$=as$+"6-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"7-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"8-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
		Next
				;;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"9-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
		Next
				;;debuglog as$
		SeekFile(fin,(((a+24)-1)*130)+88)
		hire\SP=ReadShort(fin)
		hire\MSP=ReadShort(fin)
		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		hire\GEMS=temp1+(temp2*256)
		hire\HP=ReadShort(fin)
		hire\MHP=ReadShort(fin)
		;;debuglog "HIT POINTS "+hire\Hp+"/"+hire\MHp
		;;debuglog "SPELL POINTS "+hire\sp+"/"+hire\Msp

		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
		temp4=ReadByte(fin)
			hire\exper=temp1+(temp2*256)+(temp3*65536)+(temp4*16777216)
temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
			hire\cost=temp1+(temp2*256)+(temp3*65536)

;		;;debuglog Right(Hex(temp1),2)+" "+Right(Hex(temp2),2)+" "+Right(Hex(temp3),2)+" "+Right(Hex(temp4),2)+" "
		;;debuglog "Experience:"+hire\exper
	;;debuglog "cost:"+hire\cost	
		;;debuglog "Gems:"+hire\gems
		SeekFile(fin,(((a+24)-1)*130)+121)
		hire\plus=ReadByte(fin)
		;;debuglog "Has Plus:"+Right(Bin(hire\plus),8)
		SeekFile(fin,((a+24)*130))
;	For b=0 To 107
;	temp=ReadByte(fin)
;	;;debuglog 	Chr(temp)
;	Next
	;;debuglog "End Of Hireling:"+a
	;;debuglog ""

Next




End Function

Function menu(what,ID)

						temp=LoadImage("bmenu.bmp")
						temp2=LoadImage("bmcs.bmp")
If what=1
;debuglog ID
EndIf
If what=0
	For bmon.battlemonst=Each battlemonst
		If bmon\ID=ID
			EntityAlpha bmon\entity,.1
			lastent=bmon\entity
			If bmon\ID<=able
			who=Rnd(5)+1
			done=mattack(bmon\ID, who,255,0,100 )
			ElseIf bmon\archer=1 And bmon\ID<11
			who=Rnd(5)+1
			done=mattack(bmon\ID, who,255,200,0 )
			EndIf
UpdateWorld
RenderWorld
showtext()
Flip
		EndIf
	Next
Else

	For char.character =Each character
		If char\id=id
			
			;EntityAlpha char\entity,.1
			;lastent=char\entity
;HideEntity ent_battle(0)

UpdateWorld
RenderWorld
;debuglog "+++++++++"

choice=False

asd$=""
For nvis= 1 To 4
;EntityAlpha ent_battle(nvis),.2
Next
Repeat
	CameraProject camera,EntityX#(char\entity),EntityY#(char\entity),EntityZ#(char\entity)
			If ProjectedZ()>0
				If ProjectedX()>-1 And ProjectedX()<GraphicsWidth() And ProjectedY()>-1 And ProjectedY()<GraphicsHeight()
;						PositionEntity ent_battle(0),EntityX#(char\entity),EntityY#(char\entity),EntityZ#(char\entity);EntityX#(char\entity),EntityY#(char\entity),EntityZ#(char\entity)
;						PointEntity ent_battle(0),camera
;						UpdateWorld
;						RenderWorld
						a=char\id
;						PositionEntity  ent_battle(0),bx(a),by(A),bz(A)
;						RotateEntity ent_battle(0),rx(a),ry(A),rz(A)		
DrawImage temp,ProjectedX()-70,ProjectedY()-70	
If char\battle[1]=0
;EntityAlpha ent_battle(1),1
						DrawImage temp2,ProjectedX()-15,ProjectedY()-70
End If
If char\battle[2]=0
;EntityAlpha ent_battle(2),1
						DrawImage temp2,ProjectedX()-50,ProjectedY()-55


End If
If char\battle[3]=0
;EntityAlpha ent_battle(3),1
						DrawImage temp2,ProjectedX()+20,ProjectedY()-55


End If
If char\battle[4]=0;- Or char\battle[4]=2
						DrawImage temp2,ProjectedX()-70,ProjectedY()-20


;EntityAlpha ent_battle(4),1
End If


						
						showtext()
				End If
			End If

Flip

If KeyDown(29)

If KeyDown(30) 
	emy=0
	If char\battle[3]=1
		b$="shot "
		attack(char\id,1,2)
	Else If char\battle[1]=1
b$="Attacked "

		attack(char\id,1,1)
	Else
		;debuglog "Blocked"
	EndIf
	choice=True
	If emy=1
	For bmon.battlemonst=Each battlemonst
		If bmon\id=1
			;debuglog b$+bmon\name$
		EndIf
		Next
	EndIf
EndIf
EndIf
id=1
If KeyHit(30) And char\battle[1]=1
;	id=1	
	For bmon.battlemonst=Each battlemonst
		If bmon\id=1
			;debuglog "attacked "+bmon\name$
			attack(char\id,1,1)
		EndIf
		Next
	choice=True
End If
If KeyHit(33) And char\battle[2]=1
;	Repeat
	targ=choose_target()
	If targ>0
	attack(char\id,targ,1)
	done=True
	End If
	
;	Until done=True
	choice=True
End If
If KeyHit(31) And char\battle[3]=1
	;debuglog "Shot"
;	Repeat
;	If KeyHit(2) done=True :attack(char\id,1,2)
;	If KeyHit(3) done=True :attack(char\id,2,2)
;	If KeyHit(4) done=True :attack(char\id,3,2)
;	If KeyHit(5) done=True :attack(char\id,4,2)
;	If KeyHit(6) done=True :attack(char\id,5,2)
;	If KeyHit(7) done=True :attack(char\id,6,2)
;	If KeyHit(8) done=True :attack(char\id,7,2)
;	If KeyHit(9) done=True :attack(char\id,8,2)
;	If KeyHit(10) done=True :attack(char\id,9,2)
;	If KeyHit(11) done=True :attack(char\id,10,2)
	targ=choose_target()
	If targ>0
	attack(char\id,targ,1)
	done=True
	End If

;	Until done=True
	choice=True
End If

If KeyHit(46) And char\battle[4]=1
	DebugLog "C A S T S P E L L"
	choice=cast(char\ID)
;	choice=True
End If

If KeyHit(22) And char\battle[5]=1
	;debuglog "Used"
	choice=True
End If
If KeyHit(48) And char\battle[6]=1
	;debuglog "blocked"
	choice=True
	End If
If KeyHit(19) And char\battle[7]=1
	;debuglog "Ran"
	choice=True
	End If
If KeyHit(18) And char\battle[8]=1
	;debuglog "Exchanged"
;	choice=True
End If
If KeyHit(47) And char\battle[9]=1
	;debuglog "Viewed"
End If
;	choice=True
Until choice=True
;WaitKey
		EndIf
	Next 
EndIf	
;EntityAlpha lastent,1
End Function


Function showtext()
	

	


	
	DrawImage monbox,GraphicsWidth()-225,15	
	DrawImage charbox,(GraphicsWidth()/2)-(GraphicsWidth()/26),(GraphicsWidth()/2)+(GraphicsWidth()/10)

For bmon.battlemonst= Each battlemonst
		If bmon\undead=1
		Color 25,25,25
	ElseIf bmon\airencased=1
		Color 0,255,255
	ElseIf bmon\waterencased=1
		Color 0,0,255
	ElseIf bmon\earthencased=1
		Color 123,129,58
	ElseIf bmon\fireencased=1
		Color 240,120,0
	Else
		Color 255,255,255
	EndIf
		CameraProject camera,EntityX#(bmon\entity),EntityY#(bmon\entity),EntityZ#(bmon\entity)
		If ProjectedZ()>0
			If ProjectedX()>-1 And ProjectedX()<GraphicsWidth() And ProjectedY()>-1 And ProjectedY()<GraphicsHeight()
				If bmon\ID<=able
				Text ProjectedX()-30,ProjectedY()-30,"@"+bmon\name$
				Else
				Text ProjectedX()-30,ProjectedY()-30,bmon\name$
				End If
			End If
		End If
		If bmon\hp<bmon\ohp bmon\Status="..../HURT"
		;debuglog "BMON\ID="+bmon\ID
		If bmon\undead=1
		Color 25,25,25
	ElseIf bmon\airencased=1
		Color 0,255,255
	ElseIf bmon\waterencased=1
		Color 0,0,255
	ElseIf bmon\earthencased=1
		Color 123,129,58
	ElseIf bmon\fireencased=1
		Color 240,120,0
	Else
		Color 255,255,255
	EndIf
	If bmon\id=11
		Text GraphicsWidth()-200,(20+bmon\id*12),"+"+bmon\amount+" "+bmon\name$+"'s"
	Else
		Text GraphicsWidth()-200,(20+bmon\id*12),bmon\name$+" "+bmon\hp+bmon\status
	EndIf
	Color 255,255,255	
	Next
	For char.character= Each character
		If char\entity<>0
			CameraProject camera,EntityX#(char\entity),EntityY#(char\entity),EntityZ#(char\entity)
			If ProjectedZ()>0
				If ProjectedX()>-1 And ProjectedX()<GraphicsWidth() And ProjectedY()>-1 And ProjectedY()<GraphicsHeight()
					If char\ID<=able
						Text ProjectedX()-30,ProjectedY()-30,"@"+char\name$
					Else
						Text ProjectedX()-30,ProjectedY()-30,char\name$
					EndIf
				End If
			End If

			
			Text (GraphicsWidth()/2)+(nyoffset(char\id)*(GraphicsWidth()/4)),(GraphicsHeight()-GraphicsHeight()/6)+(nxoffset(char\id)*17),char\id+" "+char\name$+" "+char\hp
		
		EndIf
	Next

End Function


Function attack(Chara,Monst,what) ; used for attack,fight and shoot.
weapon=check_item(chara,what)
If weapon=0
damage=3
;debuglog "NONE"
Else
	For item.item=Each item
		If item\id=weapon
			damage=item\damage
			;debuglog item\name$+" Damage: 1-"+damage
		EndIf
	Next
EndIf
For char.character=Each character
		If attacked=True Exit
If char\id=chara
	message$=char\name$+" "
	times=Rnd((char\lvl/7))+1
	DebugLog char\name$
	EndIf
Next
For bmon.battlemonst=Each battlemonst
			If attacked=True Exit
	If bmon\ID=Monst
	CameraProject camera,EntityX#(bmon\entity),EntityY#(bmon\entity),EntityZ#(bmon\entity)
		For a=1 To times
			dam=Rnd(damage)
			If dam>0 hits=hits+1
			tdam=tdam+dam
			bmon\hp=bmon\hp-dam
			
		If attacked=True Exit
		Next
			;Text ProjectedX(),ProjectedY(),tDam+"    "+hits
			message$=message$+"attacks "+times+" times and hits "+hits
			message2$="times for "+tdam+" damage."
		If bmon\hp<1 
				message3$="...And Goes Down"
				expgained=expgained+bmon\EXPEr
				dead=True
				attacked=True
				Exit
			EndIf

;		;debuglog 
	EndIf
	If attacked=True Exit

Next
If attacked=True
EndIf
DrawImage txtbox,(GraphicsWidth()/2)-(Len(message$)*4)-20,GraphicsHeight()/2-10
Text (GraphicsWidth()/2)-(Len(message$)*4),GraphicsHeight()/2,message$
Text (GraphicsWidth()/2)-(Len(message2$)*4),(GraphicsHeight()/2)+16,message2$
Text (GraphicsWidth()/2)-(Len(message3$)*4),(GraphicsHeight()/2)+32,message3$

Flip
Delay dlay
Cls
UpdateWorld
RenderWorld
showtext
Flip
VWait
End Function

Function mattack(monst,chara,R=255,G=0,B=0)
For bmon.battlemonst = Each battlemonst
	
	If bmon\ID=monst
	message$=bmon\name$+" "
		dam=Rnd(bmon\damage)
		DebugLog dam
		
	EndIf
Next
For char.character= Each character
If char\id=Chara
	If char\HP<=0
		chara=chara+1
		If chara=7
		 chara=0
		 EndIf
	End If
	EndIf
Next

For char.character= Each character
If char\id=Chara
		char\HP=char\HP-dam
			message$=message$+"attacks "+times+" times and hits "+hits
			message2$="times for "+dam+" damage."

		DebugLog char\Name$+" was hit for "+dam+" Damage"
EndIf	
Next
showtext()
;Cls
;RenderWorld
Color R,G,B
DrawImage txtbox,(GraphicsWidth()/2)-(Len(message$)*4)-20,GraphicsHeight()/2-10
Text (GraphicsWidth()/2)-(Len(message$)*4),GraphicsHeight()/2,message$
Text (GraphicsWidth()/2)-(Len(message2$)*4),(GraphicsHeight()/2)+16,message2$
Flip
Delay dlay*2
Color 255,255,255
Return done
End Function


Function cast(chara)
b=2
For char.character=Each character

	If char\ID=chara
;		DebugLog char\name$+" CLASS:"+char\class
		DrawImage monbox,0,0
		Text 30,0,"Which Spell Level?"
		For a=1 To char\splvl
		If a<10
		Text 50,a*16,"Level "+a
		EndIf
		Next
		Flip
		done=False
		Repeat
	If KeyHit(2) done=True :ls=0 : hs=6 :splvl=1
	If KeyHit(3) done=True :ls=7 : hs=13:splvl=2
	If KeyHit(4) done=True :ls=14 : hs=19:splvl=3
;spells not in yet
	If KeyHit(5) done=True :ls=20 : hs=25:splvl=4
	If KeyHit(6) done=True :ls=26 : hs=30:splvl=5
	If KeyHit(7) done=True :ls=31 : hs=35:splvl=6
	If KeyHit(8) done=True :ls=36 : hs=39:splvl=7
	If KeyHit(9) done=True :ls=40 : hs=43:splvl=8
	If KeyHit(10) done=True:ls=44 : hs=47:splvl=9
	If KeyHit(14) 
		done=True
		back=True
		Cls
		RenderWorld
		UpdateWorld
		showtext()
		Flip
		
	EndIf
	Until done=True
	If back=True Return False
Cls

UpdateWorld
RenderWorld
showtext
done=False
							DrawImage splbox,0,0
							Text 2,0,"Choose Spell"
							Color 255,0,0
							Text 2,20,"----------------------------------------"
							
		If char\class=1 Or char\class=3
			For a=ls To hs   ; limits to spell level 3 at the moment
;				DebugLog "spell:"+a+" is="+char\spell[a]
					For cleric.clerical=Each clerical
						If cleric\ID=a+1
				If char\spell[a]=1

							If cleric\combat=0 Color 0,255,0
							If cleric\combat=1 Color 255,0,0
							If cleric\combat=2 Color 255,255,255
		
							Text 2,b*16,"Spell: "+cleric\Level+"\"+cleric\SPell
							Text 90,b*16,cleric\name$
				EndIf
							b=b+1
						EndIf
					Next
				
			Next
			Flip
	Repeat
		Select splvl
			Case 1
			
			If KeyHit(2): spell=1 :done=True :EndIf
			If KeyHit(3): spell=2 :done=True:EndIf
			If KeyHit(4): spell=3 :done=True:EndIf
			If KeyHit(5): spell=4 :done=True:EndIf
			If KeyHit(6): spell=5 :done=True:EndIf
			If KeyHit(7): spell=6 :done=True:EndIf
			If KeyHit(8): spell=7 :done=True:EndIf
		
			Case 2
			If KeyHit(2): spell=8 :done=True:EndIf
			If KeyHit(3): spell=9 :done=True:EndIf
			If KeyHit(4): spell=10 :done=True:EndIf
			If KeyHit(5): spell=11 :done=True:EndIf
			If KeyHit(6): spell=12 :done=True:EndIf
			If KeyHit(7): spell=13 :done=True:EndIf
			If KeyHit(8): spell=14 :done=True:EndIf
			
			Case 3
			If KeyHit(2): spell=15 :done=True:EndIf
			If KeyHit(3): spell=16 :done=True:EndIf
			If KeyHit(4): spell=17 :done=True:EndIf
			If KeyHit(5): spell=18 :done=True:EndIf
			If KeyHit(6): spell=19 :done=True:EndIf
			If KeyHit(7): spell=20 :done=True:EndIf


		Case 4
		If KeyHit(2): spell=21 :done=True:EndIf
		If KeyHit(3): spell=22 :done=True:EndIf
		If KeyHit(4): spell=23 :done=True:EndIf
		If KeyHit(5): spell=24 :done=True:EndIf
		If KeyHit(6): spell=25 :done=True:EndIf
		If KeyHit(7): spell=26 :done=True:EndIf


		Case 5
		If KeyHit(2): spell=27 :done=True:EndIf
		If KeyHit(3): spell=28 :done=True:EndIf
		If KeyHit(4): spell=29 :done=True:EndIf
		If KeyHit(5): spell=30 :done=True:EndIf
		If KeyHit(6): spell=31 :done=True:EndIf
		Case 6
		If KeyHit(2): spell=32 :done=True:EndIf
		If KeyHit(3): spell=33 :done=True:EndIf
		If KeyHit(4): spell=34 :done=True:EndIf
		If KeyHit(5): spell=35 :done=True:EndIf
		If KeyHit(6): spell=36 :done=True:EndIf

		Case 7
		If KeyHit(2): spell=37 :done=True:EndIf
		If KeyHit(3): spell=38 :done=True:EndIf
		If KeyHit(4): spell=39 :done=True:EndIf
		If KeyHit(5): spell=40 :done=True:EndIf
		Case 8
		If KeyHit(2): spell=41 :done=True:EndIf
		If KeyHit(3): spell=42 :done=True:EndIf
		If KeyHit(4): spell=43 :done=True:EndIf
		If KeyHit(5): spell=44 :done=True:EndIf
		Case 9
		If KeyHit(2): spell=45 :done=True:EndIf
		If KeyHit(3): spell=46 :done=True:EndIf
		If KeyHit(4): spell=47 :done=True:EndIf
		If KeyHit(5): spell=48 :done=True:EndIf

		End Select
		If spell<>0
		If char\spell[spell-1]=0 done=False
		EndIf
		For cleric.clerical=Each clerical
		If cleric\ID=spell
			If cleric\combat=0
				done=False
			End If
		End If
		Next
		Until done=True
		Select spell
			Case 1
				targ=choose_target()
				cs_1_1(char\ID,targ)
			Case 2
				cs_1_2(char\ID)
			Case 3
				cs_1_3(char\ID)
			Case 4
				targ=choose_target(1)
				cs_1_4(char\ID,targ)
			Case 5

				cs_1_5(char\ID)
			Case 6
			targ=choose_target(1)
				cs_1_6(char\ID,targ)
			Case 7
				cs_1_7(char\ID)
			Case 8
			
			targ=choose_target(1)
				cs_2_1(char\ID,targ)
			Case 9
				targ=choose_target()
				cs_2_2(char\ID,targ)
			Case 10
			cs_2_3(char\ID)		
			Case 11
				targ=choose_target()
				cs_2_4(char\ID,targ)
			Case 12
			cs_2_5(char\ID)
			Case 13
			targ=choose_target()
			cs_2_6(char\ID,targ)
			Case 14
			targ=choose_target()
			cs_2_7(char\ID,targ)
			Case 15
				targ=choose_target()
				cs_3_1(char\ID,targ)
			Case 16

				cs_3_2(char\ID)
			Case 17
				targ=choose_target()
				cs_3_3(char\ID,targ)
			Case 18
				targ=choose_target()
				cs_3_4(char\ID,targ)
			Case 19 
			
				cs_3_5(char\ID)
			Case 20
			
				cs_3_6(char\ID)
			Case 21
				targ=choose_target() 
				cs_4_1(char\ID,targ)

			Case 22
				
				cs_4_2(char\ID)

			Case 23
							targ=choose_target(1) 
				cs_4_3(char\ID,targ)

			Case 24
							targ=choose_target(1) 
				cs_4_4(char\ID,targ)

			Case 25
				
				cs_4_5(char\ID)

			Case 26
				cs_4_6(char\ID)

			Case 27
							targ=choose_target() 
				cs_5_1(char\ID,targ)

			Case 28
				targ=choose_target() 
				cs_5_2(char\ID,targ)

			Case 29
				targ=choose_target(1) 
				cs_5_3(char\ID,targ)
			Case 30
							targ=choose_target(1) 
				cs_5_4(char\ID,targ)

			Case 31
							targ=choose_target(1) 
				cs_5_5(char\ID,targ)

			Case 32
				cs_6_1(char\ID)

			Case 33
							targ=choose_target(1) 
				cs_6_2(char\ID,targ)

			Case 34
							targ=choose_target(1) 
				cs_6_3(char\ID,targ)

			Case 35
							targ=choose_target() 
				cs_6_4(char\ID,targ)

			Case 36
				cs_6_5(char\ID,targ)

			Case 37
							targ=choose_target() 
				cs_7_1(char\ID,targ)

			Case 38
							targ=choose_target() 
				cs_7_2(char\ID,targ)

			Case 39
							targ=choose_target() 
				cs_7_3(char\ID,targ)

			Case 40
							targ=choose_target(1) 
				cs_7_4(char\ID,targ)

			Case 41
							targ=choose_target()
				cs_8_1(char\ID,targ)

			Case 42
							targ=choose_target() 
				cs_8_2(char\ID,targ)

			Case 43
				targ=choose_target()
				cs_8_3(char\ID,targ)

			Case 44
				
				cs_8_4(char\ID,targ)

			Case 45
				cs_9_1(char\ID)

			Case 46		
				cs_9_2(char\ID)

			Case 47
				targ=choose_target(1) 
				cs_9_3(char\ID,targ)

			Case 48
				cs_9_4(char\ID,targ)

			End Select

		EndIf
		If char\class=2 Or char\class=4
			For a=ls To hs   ; limits to spell level 3 at the moment
;				DebugLog "spell:"+a+" is="+char\spell[a]
					For sorc.sorceror=Each sorceror
						If sorc\ID=a+1

				If char\spell[a]=1
							If sorc\combat=0 Color 0,255,0
							If sorc\combat=1 Color 255,0,0
							If sorc\combat=2 Color 255,255,255
							DrawImage splbox,0,0
							Text 0,b*16,"Spell: "+sorc\Level+"-"+sorc\SPell
							Text 100,b*16,sorc\name$
				EndIf
							b=b+1
;							DebugLog sorc\desc[0]
;							DebugLog sorc\desc[1]
;							DebugLog sorc\desc[2]
;							DebugLog sorc\desc[3]
;							DebugLog sorc\desc[4]									
						EndIf
					Next
				
			Next
						Flip
						FlushKeys
	Repeat
		Select splvl
			Case 1
			
			If KeyHit(2): spell=1 :done=True :EndIf
			If KeyHit(3): spell=2 :done=True:EndIf
			If KeyHit(4): spell=3 :done=True:EndIf
			If KeyHit(5): spell=4 :done=True:EndIf
			If KeyHit(6): spell=5 :done=True:EndIf
			If KeyHit(7): spell=6 :done=True:EndIf
			If KeyHit(8): spell=7 :done=True:EndIf
		
			Case 2
			If KeyHit(2): spell=8 :done=True:EndIf
			If KeyHit(3): spell=9 :done=True:EndIf
			If KeyHit(4): spell=10 :done=True:EndIf
			If KeyHit(5): spell=11 :done=True:EndIf
			If KeyHit(6): spell=12 :done=True:EndIf
			If KeyHit(7): spell=13 :done=True:EndIf
			If KeyHit(8): spell=14 :done=True:EndIf
			
			Case 3
			If KeyHit(2): spell=15 :done=True:EndIf
			If KeyHit(3): spell=16 :done=True:EndIf
			If KeyHit(4): spell=17 :done=True:EndIf
			If KeyHit(5): spell=18 :done=True:EndIf
			If KeyHit(6): spell=19 :done=True:EndIf
			If KeyHit(7): spell=20 :done=True:EndIf

		Case 4
		If KeyHit(2): spell=21 :done=True:EndIf
		If KeyHit(3): spell=22 :done=True:EndIf
		If KeyHit(4): spell=23 :done=True:EndIf
		If KeyHit(5): spell=24 :done=True:EndIf
		If KeyHit(6): spell=25 :done=True:EndIf
		If KeyHit(7): spell=26 :done=True:EndIf


		Case 5
		If KeyHit(2): spell=27 :done=True:EndIf
		If KeyHit(3): spell=28 :done=True:EndIf
		If KeyHit(4): spell=29 :done=True:EndIf
		If KeyHit(5): spell=30 :done=True:EndIf
		If KeyHit(6): spell=31 :done=True:EndIf
		
		Case 6
		If KeyHit(2): spell=32 :done=True:EndIf
		If KeyHit(3): spell=33 :done=True:EndIf
		If KeyHit(4): spell=34 :done=True:EndIf
		If KeyHit(5): spell=35 :done=True:EndIf
		If KeyHit(6): spell=36 :done=True:EndIf
		Case 7
		If KeyHit(2): spell=37 :done=True:EndIf
		If KeyHit(3): spell=38 :done=True:EndIf
		If KeyHit(4): spell=39 :done=True:EndIf
		If KeyHit(5): spell=40 :done=True:EndIf
		Case 8
		If KeyHit(2): spell=41 :done=True:EndIf
		If KeyHit(3): spell=42 :done=True:EndIf
		If KeyHit(4): spell=43 :done=True:EndIf
		If KeyHit(5): spell=44 :done=True:EndIf
		Case 9
		If KeyHit(2): spell=45 :done=True:EndIf
		If KeyHit(3): spell=46 :done=True:EndIf
		If KeyHit(4): spell=47 :done=True:EndIf
		If KeyHit(5): spell=48 :done=True:EndIf
		End Select
	
		If spell<>0
		If char\spell[spell-1]=0 done=False
		EndIf
		For sorc.sorceror=Each sorceror
		If sorc\ID=spell
			If sorc\combat=0
				done=False
			End If
		End If
		Next
		Until done=True
		Select spell
			Case 1
				ss_1_1(char\ID)
			Case 2
				ss_1_2(char\ID)
			Case 3
				targ=choose_target()
;				DebugLog char\ID
				;DebugLog targ
				ss_1_3(char\ID,targ)
			Case 4
				targ=choose_target()
;			DebugLog char\ID
;				DebugLog targ
				ss_1_4(char\ID,targ)
			Case 5
				ss_1_5(char\ID)
			Case 6
				ss_1_6(char\ID,targ)
			Case 7
				targ=choose_target()
;			DebugLog char\ID
;				DebugLog targ
	
				ss_1_7(char\ID,targ)
			Case 8
				ss_2_1(char\ID)


			Case 9
				targ=choose_target()
			ss_2_2(char\ID,targ)

			Case 10
			targ=choose_target()
			ss_2_3(char\ID,targ)		
			Case 11
				ss_2_4(char\ID)
			Case 12
			ss_2_5(char\ID)
			Case 13
			ss_2_6(char\ID)
			Case 14
			ss_2_7(char\ID)
			Case 15
				targ=choose_target()
				ss_3_1(char\ID,targ)
			Case 16
				ss_3_2(char\ID,0,0)
			Case 17
				ss_3_3(char\ID)
			Case 18
				targ=choose_target()
				ss_3_4(char\ID,targ)
			Case 19 
				targ=choose_target()
				ss_3_5(char\ID,targ)
			Case 20
				ss_3_6(char\ID)
			Case 21
				targ=choose_target()
				ss_4_1(char\ID,targ)
			Case 22
				targ=choose_target()
				ss_4_3(char\ID,targ)
			Case 23
				targ=choose_target()
				ss_4_3(char\ID,targ)
			Case 24
				ss_4_4(char\ID)
			Case 25
				
				
				ss_4_5(char\ID)
			Case 26

				ss_4_6(char\ID)

			Case 27
				targ=choose_target()
				ss_5_1(char\ID,targ)

			Case 28
				targ=choose_target()
				ss_5_2(char\ID,targ)

			Case 29 
				targ=choose_target()
				ss_5_3(char\ID,targ)

			Case 30
				
				ss_5_4(char\ID)

			Case 31
				
				ss_5_5(char\ID)

			Case 32
				targ=choose_target()
				ss_6_1(char\ID,targ)

			Case 33
				
				ss_6_2(char\ID)

			Case 34
				targ=choose_target()
				ss_6_3(char\ID,targ)

			Case 35
				targ=choose_target()
				ss_6_4(char\ID,targ)

			Case 36
				targ=choose_target()
				ss_6_5(char\ID,targ)

			Case 37

				ss_7_1(char\ID)

			Case 38
				targ=choose_target()
				ss_7_2(char\ID,targ)

			Case 39
				ss_7_3(char\ID)

			Case 40
				targ=choose_target()
				ss_7_4(char\ID,targ)

			Case 41
				targ=choose_target()
				ss_8_1(char\ID,targ)

			Case 42
				targ=choose_target()
				ss_8_2(char\ID,targ)

			Case 43
				ss_8_3(char\ID)

			Case 44
				
				ss_8_4(char\ID)

			Case 45
				targ=choose_target()
				ss_9_1(char\ID,targ)

			Case 46
				targ=choose_target()
				ss_9_2(char\ID,targ)

			Case 47
				
				ss_9_3(char\ID)

			Case 48
				
				ss_9_4(char\ID)

			End Select
		EndIf
	EndIf
Next
Color 255,255,255
If targ>0
For bmon.battlemonst=Each battlemonst
If bmon\ID=targ
	If bmon\HP<=0
		updatemonsters(bmon\ID)
	EndIf
	EndIf
Next
EndIf
Return True
End Function

Function check_item(Chara,kind)
	item=0
For char.character=Each character

	If char\id=chara
		For a=0 To 5
			
			Select kind ; 1=weapon, 2= bows , 3=shields, 4= armour, 5= helmet, 6=keys, 7=misc
			Case 1 ; 1   -  91
				If char\equiped[a]>=1 And  char\equiped[a]<=91
					item=char\equiped[a]
					;debuglog item

				EndIf
			Case 2 ; 92  - 110
				If char\equiped[a]>=92 And char\equiped[a]<=110
					item=char\equiped[a]
				EndIf

			Case 3 ; 115 - 126
				If char\equiped[a]>=115 And char\equiped[a]<=126
					item=char\equiped[a]
				EndIf

			Case 4 ; 127 - 154
				If char\equiped[a]>=127 And char\equiped[a]<=154
					item=char\equiped[a]
				EndIf

			Case 5 ; 155 - 159
				If char\equiped[a]>=155 And char\equiped[a]<=159
					item=char\equiped[a]
				EndIf
			Case 6
				If char\equiped[a]>=111 And char\equiped[a]<=114
					item=char\equiped[a]
				EndIf
			Case 7
				If char\equiped[a]>=159 And char\equiped[a]<=255
					item=char\equiped[a]
				EndIf
			End Select
		Next
	EndIf
Next
;debuglog item
Return item
End Function

Function updatemonsters(MONST)
	For bmon.battlemonst=Each battlemonst
	
		;debuglog bmon\ID
		If bmon\ID=11
			bmon\Amount=bmon\amount-1
			realid=bmon\realID
			hp=bmon\HP
			;debuglog readlid
			admnst=True
			If bmon\amount<=0 
			For fade=10 To 0 Step -1
			;EntityAlpha bmon\entity,fade/10

			;UpdateWorld
			;RenderWorld
			;Delay 1
			;showtext()
			;Flip
			Next
			FreeEntity bmon\entity
			Delete bmon
			EndIf	
						
		Else If bmon\ID>=monst
			If bmon\ID=monst
						For fade=10 To 0 Step -1
		

			;UpdateWorld
			;RenderWorld
			sc#=fade/10
			;ScaleEntity bmon\entity,sc#,sc#,sc#
			;Delay 1
			;showtext()
			;Flip
			Next

			;	FreeEntity bmon\entity
				Delete bmon
			Else 
				bmon\ID=bmon\ID-1
				PositionEntity bmon\entity,posx(bmon\id),10,posz(bmon\id)
			EndIf
		End If
		
			
		Next
	If admnst=True
	addmonst(realid,hp)
	EndIf	
End Function

Function addmonst(realid,hp)
	bmon.battlemonst=New battlemonst
			
			For mon.monster=Each monster
				If mon\id=realid
					bmon\ID=10
					bmon\realID=mon\ID
					bmon\name$=mon\name$
					bmon\HP=hp
					bmon\OHP=mon\hp
					bmon\SP=mon\sp
					bmon\AC=mon\ac
					bmon\pabil=mon\pabil
					bmon\oabil=mon\oabil
					bmon\sabil=mon\sabil			
					bmon\Abil1=mon\abil1
					bmon\Abil2=mon\abil2
					bmon\Exper=mon\exper
					bmon\Gold=mon\gold
					bmon\MRes=mon\mres
					bmon\damage=mon\damage
					bmon\Picture=mon\picture
					bmon\Undead=mon\undead
					bmon\Archer=mon\archer
					bmon\flee=mon\flee
					bmon\speed=mon\speed
					; when there are 3d models it will load it in for now, it is a cube coloured depending on the monster.
					bmon\entity=CreateCube()
					PositionEntity bmon\entity,posx(bmon\id),10,posz(bmon\id)
	;				txt_atck=LoadTexture("D:\Adams\Blitz3d\mm2\Models\batttle\cast.bmp")
;					EntityTexture bmon\entity,txt_atck
					If mon\Colour=0	EntityColor bmon\entity,mon\ID,0,0
					If mon\Colour=1	EntityColor bmon\entity,0,mon\ID,0
					If mon\Colour=2	EntityColor bmon\entity,0,0,mon\ID

				EndIf
			Next	
			Insert bmon Before Last battlemonst			
End Function



Function choose_target(guys=0)

Cls

UpdateWorld
RenderWorld
	Color 255,255,255
showtext
	Color 0,255,0
	DrawImage monbox,0,0
	Text 2,0,"Choose target"
	Color 255,255,255
	b=1
	a=1
If guys=0
	For bmon.battlemonst=Each battlemonst
	Text 0,b*12," "+a+" = "+bmon\name$
	b=b+1
	a=a+1
	Next
	Flip
	Repeat
	If KeyHit(2) done=True : target=1
	If KeyHit(3) done=True : target=2
	If KeyHit(4) done=True : target=3
	If KeyHit(5) done=True : target=4
	If KeyHit(6) done=True : target=5
	If KeyHit(7) done=True : target=6
	If KeyHit(8) done=True : target=7
	If KeyHit(9) done=True : target=8
	If KeyHit(10) done=True: target=9
	If KeyHit(11) done=True: target=10
	Until done=True
	choice=True
	Return target
	Else
	For char.character=Each character
	If char\ID<=6
	Text 0,b*16," "+a+" = "+char\name$
	b=b+1
	a=a+1
	EndIf
	Next
	Flip
	Repeat

	If KeyHit(2) done=True : target=1
	If KeyHit(3) done=True : target=2
	If KeyHit(4) done=True : target=3
	If KeyHit(5) done=True : target=4
	If KeyHit(6) done=True : target=5
	If KeyHit(7) done=True : target=6
Until done=True
	choice=True
	Return target

EndIf
End Function

Function encasement()
	For bmon.battlemonst=Each battlemonst
		If bmon\airencased=1
			bmon\HP=bmon\HP-10	
		Else If bmon\waterencased=1
					bmon\HP=bmon\HP-20
		Else If bmon\earthencased=1
					bmon\HP=bmon\HP-40
		Else If bmon\fireencased=1
					bmon\HP=bmon\HP-80
		EndIf
		If bmon\hp<=0
		updatemonsters(bmon\ID)
		EndIf
	Next
End Function


Function beta_text()
Cls
Text 0,0,"Might and magic II Remake Battle engine beta."
Text 0,16,"Key:  A - Attack"
Text 0,32,"Key:  F - Fight"
Text 0,48,"Key:  S - Shoot"
Text 0,64,"Key:  C - Cast"
Text 0,80,"Key:  B - block"
Text 0,96,"These are not implemented yet"
Color 0,255,0
Text 0,112,"Key:  E - Exchange"
Text 0,128,"Key:  V - View"
Text 0,144,"Key:  R - Run"
Text 0,160,"Key:  U - Use"
Color 0,255,255
Text 0,176,"Follow Screen prompts for the rest."
Text 0,192,"Holding Cntr-A auto attacks, shoots or blocks."
Text 0,208,"If a spell is green you can't cast it in combat."
Text 0,224,"and most spells give out no information yet."
Text 0,240,"and you can't got out of the spell menu once you have started. 
Text 0,256,"levels 7 Or 9 are recommended For the Sorceror (Cassandra/Sure Valla)"
Text 0,272,"levels 7 Or 8 are recommended For the clerics (Gene Eric/Terwin III)"
Text 0,288,"Cleric spell 9-2 Holy word kills undead. names are red"
Text 0,304,"and 7-1 or 9-3 are cool spells for the sorceror's"




WaitKey
Flip
End Function