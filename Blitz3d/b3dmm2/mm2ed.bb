;XLnt-GUI CODE
Include "XLNT-GUI_V1.84.BB"
Graphics 800,600,16,2

Type map
	Field Area		; area number 0-59
	Field envion	; 0 town, 1=cavern, 2=castle, 3=outside
	Field Name$		; Name of area,ie A1,middlegate.
	Field Exits[4]
	Field mapdata$[256]
	Field collmap$[256] 
	Field roofdata[256] ; 0 empty, 1 roof
	;Field roofdata2[256]
	Field events[256] ;=0 no event, 1=event, later the event.dat will be used to give them there proper numbers
	Field meshsurf,roofsurf,collsurf
	Field surfloc[2]
	Field mesh
	;Field omesh[256]
	Field coll
	Field show=0
	Field roof
	Field flor
End Type
Dim temparray(256)
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
skill(13)="Pathfinder
skill(14)="PickPocket
skill(15)="Soldier"
cskill$=""
For a=0 To 15
cskill$=cskill$+skill(a)+"#"
Next

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
Global char_no=0
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
Type item
	Field name$
	Field Value
	Field Damage
	Field ID
	Field Usableby
	Field usa[8]
	Field effect$[2]; 0 is type, 1 amount
	Field bonus$[2] ; 0 is type, 1 amount
End Type

GUI_GFXSETUP()

Dim amap(15,15)

Global graphicsfile = LoadAnimImage("gfx/tiles.jpg",32,32,0,255)


Global cursorx,cursory
Global gx,gy
Global currenttile=0,ct=0,collis=0

Global rpik,rpik2
Global rcol,rcol1,rcol2

;Map Editor GUI DATA
Global MapEdit.WINDOW=GUI_WINDOW(604,45,123,306,"Map Editor",0,0,0,0,0)
;Gadgets....
Global cmd_mploadmap.GADGET=GUI_BUTTUN(MapEdit,28,28,57,"Load Map",1,"")
Global cmd_mpsavemap.GADGET=GUI_BUTTUN(MapEdit,28,52,56,"Save Map",1,"")
Global img_tile.GADGET=GUI_IMAGEBOX(MapEdit,44,92,32,32,tile,0,1,"")
Global msg_cblock.GADGET=GUI_TEXT(MapEdit,20,76,100,100,"Current Block","")
Global cmd_mpedmp.GADGET=GUI_switch(MapEdit,20,136,80,"Edit Maps",1,1,1,"")
Global cmd_mpedev.GADGET=GUI_switch(MapEdit,20,154,80,"Edit Events",0,1,1,"")
Global cmd_mpedcl.GADGET=GUI_switch(MapEdit,20,172,80,"Edit Collision",0,1,1,"")
Global msg_mmcm.GADGET=GUI_TEXT(MapEdit,20,188,100,100,"MAP_NAME","")
Global cmd_mpbn.GADGET=GUI_BUTTUN(MapEdit,84,92,16,"+",1,"")
Global cmd_mpbp.GADGET=GUI_BUTTUN(MapEdit,84,108,16,"-",1,"")
Global cmd_mpprev.GADGET=GUI_BUTTUN(MapEdit,12,220,40,"<<",1,"")
Global cmd_mpnext.GADGET=GUI_BUTTUN(MapEdit,68,220,40,">>",1,"")
Global cmd_mpabout.GADGET=GUI_BUTTUN(MapEdit,44,252,40,"About",1,"")
Global cmd_mpquit.GADGET=GUI_BUTTUN(MapEdit,44,276,40,"Back",1,"")


Global MAIN.WINDOW
Global cmd_items.GADGET,cmd_monsters.GADGET,cmd_char.GADGET,cmd_maps.GADGET,cmd_attrib.GADGET
Global frm_1.GADGET,cmd_events.GADGET,cmd_default.GADGET,cmd_About.GADGET,cmd_quit.GADGET
Global frm_2.GADGET,msg_1.GADGET


;About
Global About.WINDOW=GUI_WINDOW(237,180,250,100,"About",0,0,1,0,0)
;Gadets....
Global face$="tiles.jpg"
Global img_frame.GADGET=GUI_IMAGEBOX(ABOUT,20,28,56,56,Face$,100,0,"")
Global cmd_Ok.GADGET=GUI_BUTTUN(ABOUT,196,52,40,"OK",1,"")
Global msg_a1.GADGET=GUI_TEXT(ABOUT,92,20,100,100,"Might and Magic II - Editor","")
Global msg_a2.GADGET=GUI_TEXT(ABOUT,92,42,100,100,"�2002 Adam Templeton","")
Global msg_a3.GADGET=GUI_TEXT(ABOUT,92,74,100,100,"Version 0.4 ","")
 
;MM2ED GUI DATA
MAIN.WINDOW=GUI_WINDOW(233,200,137,204,"MM2ED",1,0,1,0,0)
;Gadgets....
cmd_items.GADGET=GUI_BUTTUN(MAIN,12,60,40,"Items",1,"")
cmd_monsters.GADGET=GUI_BUTTUN(MAIN,68,60,58,"Monsters",1,"")
cmd_char.GADGET=GUI_BUTTUN(MAIN,12,84,65,"Characters",1,"")
cmd_maps.GADGET=GUI_BUTTUN(MAIN,84,84,40,"Maps",1,"")
cmd_attrib.GADGET=GUI_BUTTUN(MAIN,20,108,95,"Map Attributes",0,"")
cmd_events.GADGET=GUI_BUTTUN(MAIN,12,132,41,"Events",0,"")
cmd_default.GADGET=GUI_BUTTUN(MAIN,68,132,53,"Def Party",0,"")
frm_1.GADGET=GUI_FRAME(MAIN,4,52,128,104,"",0,"")
cmd_quit.GADGET=GUI_BUTTUN(MAIN,84,172,40,"Quit",1,"")
cmd_About.GADGET=GUI_BUTTUN(MAIN,12,172,40,"About",1,"")
frm_2.GADGET=GUI_FRAME(MAIN,4,164,128,32,"",2,"")
msg_1.GADGET=GUI_TEXT(MAIN,20,20,128,16,"Might and Magic II               Editor","")
 
GUI_OPENWIN(MAIN)

Global MONST.WINDOW
Global cmd_mload.GADGET,cmd_msave.GADGET,cmd_mback.GADGET,cmd_mabout.GADGET,frm_m2.GADGET
Global tab_mmain.GADGET,tab_mattrib.GADGET,frm_m1.GADGET,cmd_mPrev.GADGET,cmd_mnext.GADGET
Global txt_mname.GADGET,sld_mhp.GADGET,sld_mexp.GADGET,int_Mac.GADGET,int_mgold.GADGET
Global int_mgems.GADGET,msg_m1.GADGET,msg_m2.GADGET,msg_m3.GADGET,msg_m4.GADGET
Global msg_m5.GADGET,int_mres.GADGET
 
;Monster Editor GUI DATA
MONST.WINDOW=GUI_WINDOW(233,200,300,200,"Monster Editor",0,0,1,1,0)
;Gadgets....
cmd_msave.GADGET=GUI_BUTTUN(MONST,68,172,40,"Save",1,"")
cmd_mload.GADGET=GUI_BUTTUN(MONST,20,172,40,"Load",1,"")
cmd_mabout.GADGET=GUI_BUTTUN(MONST,188,172,40,"About",1,"")
cmd_mback.GADGET=GUI_BUTTUN(MONST,236,172,40,"Back",1,"")
tab_mmain.GADGET=GUI_TAB(MONST,12,20,30,"Main",1,"")
frm_m1.GADGET=GUI_FRAME(MONST,4,36,288,120,"",0,"")
frm_m2.GADGET=GUI_FRAME(MONST,4,164,288,32,"",0,"")
cmd_mPrev.GADGET=GUI_BUTTUN(MONST,116,172,16,"<<",1,"")
cmd_mnext.GADGET=GUI_BUTTUN(MONST,164,172,16,">>",1,"")
txt_mname.GADGET=GUI_TXTINPUT(MONST,110,20,100,"","Name:",50,1,"")
sld_mhp.GADGET=GUI_SLIDER(MONST,140,44,50,0,0,255,0,1,"")
sld_mexp.GADGET=GUI_SLIDER(MONST,196,44,50,0,0,255,0,1,"")
;sld_MAC.GADGET=GUI_SLIDER(MONST,252,44,50,0,0,255,0,1,"")
int_mgold.GADGET=GUI_INTEGER(MONST,52,46,"",0,0,16,1,"")
int_mgems.GADGET=GUI_INTEGER(MONST,52,70,"",0,0,16,1,"")
int_mAC.GADGET=GUI_INTEGER(MONST,52,94,"",0,0,255,1,"")
int_mres.GADGET=GUI_INTEGER(MONST,52,118,"",0,0,255,1,"")
tab_mattrib.GADGET=GUI_TAB(MONST,44,20,60,"Attributes",2,"")
msg_m1.GADGET=GUI_TEXT(MONST,12,68,32,10,"Gold:","")
msg_m2.GADGET=GUI_TEXT(MONST,12,100,48,10,"Gems:","")
msg_m3.GADGET=GUI_TEXT(MONST,124,124,72,10,"Text Message","")
msg_m4.GADGET=GUI_TEXT(MONST,188,124,48,10,"Text Message","")
msg_m5.GADGET=GUI_TEXT(MONST,236,124,56,16,"Text Message","")

Global bol_mundead.GADGET=GUI_TICK(MONsT,20,52,"Undead",0,0,1,"")
Global bol_Marcher.GADGET=GUI_TICK(MONsT,20,68,"Archer",0,0,1,"")
Global frm_m5.GADGET=GUI_FRAME(MONsT,12,44,272,40,"",0,"")
Global sel_mat1.GADGET=GUI_cycle(MONsT,84,60,164,adec$,0,1,"") 
Global bol_mflee.GADGET=GUI_TICK(MONST,20,100,"Can Flee",1,0,1,"")
Global sel_mpaf.GADGET=GUI_CYCLE(MONST,124,124,80,"0#1#2#3#4#5#6#7#8#9#10#11#12#13#14#15#16#",0,1,"Ability used on Whole Party Frequency")
Global sel_mpab.GADGET=GUI_CYCLE(MONST,92,100,168,pdec$,0,1,"Ability used on Whole Party")
Global frm_m4.GADGET=GUI_FRAME(MONST,12,92,272,56,"",0,"")
Global bol_mfrnds.GADGET=GUI_TICK(MONST,20,124,"Adds Friends",0,0,1,"")

;GUI_GAD_TO_TAB(txt_mname,tab_mmain)
GUI_GAD_TO_TAB(sld_mhp,tab_mmain)
GUI_GAD_TO_TAB(sld_mexp,tab_mmain)
GUI_GAD_TO_TAB(int_Mac,tab_mmain)
GUI_GAD_TO_TAB(int_mgold,tab_mmain)
GUI_GAD_TO_TAB(int_mgems,tab_mmain)
GUI_GAD_TO_TAB(int_mres,tab_mmain)
GUI_GAD_TO_TAB(msg_m1,tab_mmain)
GUI_GAD_TO_TAB(msg_m2,tab_mmain)
GUI_GAD_TO_TAB(msg_m3,tab_mmain)
GUI_GAD_TO_TAB(msg_m4,tab_mmain)
GUI_GAD_TO_TAB(msg_m5,tab_mmain)
gui_gad_to_tab(bol_marcher,tab_mattrib)
gui_gad_to_tab(bol_mundead,tab_mattrib)
gui_gad_to_tab(sel_mat1,tab_mattrib)
gui_gad_to_tab(frm_m5,tab_mattrib)
gui_gad_to_tab(bol_mflee,tab_mattrib)
gui_gad_to_tab(bol_mfrnds,tab_mattrib)
gui_gad_to_tab(sel_mpaf,tab_mattrib)
gui_gad_to_tab(sel_mpab,tab_mattrib)
gui_gad_to_tab(frm_m4,tab_mattrib)
;Item window GUI DATA
Global Item_win.WINDOW=GUI_WINDOW(233,200,299,266,"Might and Magic II - Item Editor",0,0,1,0,0)
;Gadets....
Global cmd_iload.GADGET=GUI_BUTTUN(Item_win,12,236,40,"Load",1,"")
Global cmd_isave.GADGET=GUI_BUTTUN(Item_win,60,236,40,"Save",1,"")
Global cmd_iabout.GADGET=GUI_BUTTUN(Item_win,196,236,40,"About",1,"")
Global cmd_iback.GADGET=GUI_BUTTUN(Item_win,244,236,40,"back",1,"")
Global sel_item.GADGET=GUI_SELECTOR(Item_win,84,28,153,"One#Two#Three#",1,"")
Global frm_i1.GADGET=GUI_FRAME(Item_win,4,228,288,32,"",1,"")
Global frm_i2.GADGET=GUI_FRAME(Item_win,4,44,288,176,"Item",1,"")
Global txt_name.GADGET=GUI_TXTINPUT(Item_win,22,60,80,"","Name:",8,1,"")
Global frm_i3.GADGET=GUI_FRAME(Item_win,20,76,248,56,"Useable By",1,"")
Global bol_k.GADGET=GUI_TICK(Item_win,36,92,"Knight",0,0,1,"")
Global bol_p.GADGET=GUI_TICK(Item_win,36,108,"Paladin",0,0,1,"")
Global bol_a.GADGET=GUI_TICK(Item_win,36,124,"Archer",0,0,1,"")
Global bol_c.GADGET=GUI_TICK(Item_win,108,92,"Cleric",0,0,1,"")
Global bol_s.GADGET=GUI_TICK(Item_win,108,108,"Sorceror",0,0,1,"")
Global bol_r.GADGET=GUI_TICK(Item_win,108,124,"Robber",0,0,1,"")
Global bol_n.GADGET=GUI_TICK(Item_win,180,100,"Ninja",0,0,1,"")
Global bol_b.GADGET=GUI_TICK(Item_win,180,116,"Barbarian",0,0,1,"")
Global sel_bns.GADGET=GUI_cycle(Item_win,60,148,70,"0#1#2#3#4#5#6#7#8#9#A#B#C#D#E#F#",0,1,"")

Global int_bns.GADGET=GUI_INTEGER(Item_win,196,148,"",0,0,15,1,"")
Global msg_i1.GADGET=GUI_TEXT(Item_win,148,148,48,10,"Amount:","")
Global sel_eft.GADGET=GUI_cycle(Item_win,60,164,70,"0#1#2#3#4#5#6#7#8#9#A#B#C#D#E#F#",0,1,"")
Global int_eft.GADGET=GUI_INTEGER(Item_win,196,164,"",0,0,15,1,"")
Global msg_i2.GADGET=GUI_TEXT(Item_win,148,164,48,10,"Amount:","")
Global msg_i3.GADGET=GUI_TEXT(Item_win,12,164,40,10,"Effect","Effect When Item is Used")
Global msg_i4.GADGET=GUI_TEXT(Item_win,12,148,40,16,"Bonus:","Effect When Item is Equiped")
Global int_dmg.GADGET=GUI_INTEGER(Item_win,60,188,"",0,0,100,1,"")
Global msg_i5.GADGET=GUI_TEXT(Item_win,12,188,48,10,"Damage","Damage Done if Weapon")
Global txt_val.GADGET=GUI_TXTINPUT(Item_win,166,188,80,"","Value",5,1,"Value of Item")


Global msg_6.GADGET=GUI_TEXT(Item_win,44,28,72,10,"Item:","Value of Item")


;Character Editor GUI DATA
Global Charwin.WINDOW=GUI_WINDOW(296,98,290,323,"Might and Magic II - Character Editor",0,0,1,0,0)
;Gadgets....
Global cmd_cload.GADGET=GUI_BUTTUN(Charwin,20,292,40,"Load",1,"")
Global cmd_csave.GADGET=GUI_BUTTUN(Charwin,68,292,40,"Save",1,"")
Global cmd_cback.GADGET=GUI_BUTTUN(Charwin,228,292,40,"Back",1,"")
Global cmd_cabout.GADGET=GUI_BUTTUN(Charwin,180,292,40,"About",1,"")
Global cmd_cprev.GADGET=GUI_BUTTUN(Charwin,124,292,16,"<<",1,"")
Global cmd_cnext.GADGET=GUI_BUTTUN(Charwin,148,292,16,">>",1,"")
Global frm_c1.GADGET=GUI_FRAME(Charwin,12,284,264,32,"",0,"")
Global tab_cchar.GADGET=GUI_TAB(Charwin,4,52,72,"Character",1,"")
Global tab_cstat.GADGET=GUI_TAB(Charwin,76,52,55,"Statistics",2,"")
Global tab_citem.GADGET=GUI_TAB(Charwin,132,52,40,"Items",3,"")
Global tab_cspells.GADGET=GUI_TAB(Charwin,172,52,38,"Spells",4,"")
Global frm_c2.GADGET=GUI_FRAME(Charwin,4,68,280,208,"",4,"")
Global txt_cname.GADGET=GUI_TXTINPUT(Charwin,16,28,88,"","Name:",10,1,"")
Global sel_ctype.GADGET=GUI_CYCLE(Charwin,196,28,74,"Characters#Hirelings#",0,1,"")
Global sel_ctown.GADGET=GUI_CYCLE(Charwin,36,84,72,"NONE#Middlegate#Atlantium#Tundra#Volcania#Sandsobar#",0,1,"")
Global sel_csex.GADGET=GUI_CYCLE(Charwin,36,108,72,"Male#Female#",0,1,"")
Global sel_calign.GADGET=GUI_CYCLE(Charwin,36,132,72,"Good#Neutral#Evil#",0,1,"")
Global sel_crace.GADGET=GUI_CYCLE(Charwin,36,180,72,"Human#Elf#Dwarf#Gnome#Half-Orc#",0,1,"")
Global sel_cclass.GADGET=GUI_CYCLE(Charwin,36,156,72,"Knight#Paladin#Archer#Cleric#Sorcerer#Robber#Ninja#Barbarian#",0,1,"")
Global bol_cplus.GADGET=GUI_TICK(Charwin,180,156,"Has Plus '+'",1,0,1,"")
Global sel_cskill1.GADGET=GUI_CYCLE(Charwin,36,220,53,cskill$,0,1,"")
Global frm_c3.GADGET=GUI_FRAME(Charwin,20,72,120,128,"",0,"")
Global sel_cskill2.GADGET=GUI_CYCLE(Charwin,36,244,53,cskill$,0,1,"")
Global frm_c4.GADGET=GUI_FRAME(Charwin,20,212,120,56,"",3,"")
Global int_cfood.GADGET=GUI_INTEGER(Charwin,180,132,"Food",0,0,40,1,"")
Global txt_cgems.GADGET=GUI_TXTINPUT(Charwin,160,108,56,"","Gems",50,1,"")
Global txt_cgold.GADGET=GUI_TXTINPUT(Charwin,153,84,88,"","Gold",50,1,"")
Global bol_cgc.GADGET=GUI_TICK(Charwin,180,172,"Green Crown",0,0,1,"")
Global bol_cyc.GADGET=GUI_TICK(Charwin,180,188,"Yellow Crown",0,0,1,"")
Global bol_crc.GADGET=GUI_TICK(Charwin,180,204,"Red Crown",0,0,1,"")
Global bol_cbc.GADGET=GUI_TICK(Charwin,180,220,"Black Crown",0,0,1,"")

Global int_clvl.GADGET=GUI_INTEGER(Charwin,20,76,"Level",0,0,255,1,"")
Global int_cmgt.GADGET=GUI_INTEGER(Charwin,22,100,"MGT ",0,0,255,1,"Might")
Global int_cint.GADGET=GUI_INTEGER(Charwin,27,124," INT ",0,0,255,1,"Intellect")
Global int_cper.GADGET=GUI_INTEGER(Charwin,27,148,"PER ",0,0,255,1,"Personallity")
Global int_cend.GADGET=GUI_INTEGER(Charwin,27,172,"END ",0,0,255,1,"Endurance")
Global int_cspd.GADGET=GUI_INTEGER(Charwin,24,196,"SPD ",0,0,255,1,"Speed")
Global int_cacc.GADGET=GUI_INTEGER(Charwin,22,220,"ACC ",0,0,255,1,"Accuracy")
Global int_cluc.GADGET=GUI_INTEGER(Charwin,24,244,"LUC ",0,0,255,1,"Luck")

Global txt_chp.GADGET=GUI_TXTINPUT(Charwin,101,76,72,"","HP",5,1,"Hit Points")
Global txt_cmhp.GADGET=GUI_TXTINPUT(Charwin,198,76,72,"","/",5,1,"Max Hit Points")
Global txt_csp.GADGET=GUI_TXTINPUT(Charwin,101,100,72,"","SP",5,1,"Spell Points")
Global txt_cmsp.GADGET=GUI_TXTINPUT(Charwin,198,100,72,"","/",5,1,"Max Spell Points")

Global txt_cEXP.GADGET=GUI_TXTINPUT(Charwin,101,124,112,"","Experience",50,1,"")
Global int_cage.GADGET=GUI_INTEGER(Charwin,132,148,"Age",0,0,255,1,"")
Global int_cac.GADGET=GUI_INTEGER(Charwin,220,148,"AC",0,0,255,1,"")
Global int_csl.GADGET=GUI_INTEGER(Charwin,220,172,"SL",0,0,10,1,"")
Global int_cthv.GADGET=GUI_INTEGER(Charwin,108,172,"Thievery",0,0,255,1,"")

;Global sel_ceq1.GADGET=GUI_CYCLE(charwin,20,84,72,"One#Two#Three#",0,1,"")
;Global sel_cbp1.GADGET=GUI_CYCLE(charwin,156,84,72,"One#Two#Three#",0,1,"")
Global int_bp1.GADGET=GUI_INTEGER(Charwin,155,76,"BP-1",0,0,255,1,"")
Global int_bp2.GADGET=GUI_INTEGER(Charwin,155,100,"BP-2",0,0,255,1,"")
Global int_bp3.GADGET=GUI_INTEGER(Charwin,155,124,"BP-3",0,0,255,1,"")
Global int_bp4.GADGET=GUI_INTEGER(Charwin,155,148,"BP-4",0,0,255,1,"")
Global int_bp5.GADGET=GUI_INTEGER(Charwin,155,172,"BP-5",0,0,255,1,"")
Global int_bp6.GADGET=GUI_INTEGER(Charwin,155,196,"BP-6",0,0,255,1,"")

Global int_eq1.GADGET=GUI_INTEGER(Charwin,20,76,"EQ-1",0,0,255,1,"")
Global int_eq2.GADGET=GUI_INTEGER(Charwin,20,100,"EQ-2",0,0,255,1,"")
Global int_eq3.GADGET=GUI_INTEGER(Charwin,20,124,"EQ-3",0,0,255,1,"")
Global int_eq4.GADGET=GUI_INTEGER(Charwin,20,148,"EQ-4",0,0,255,1,"")
Global int_eq5.GADGET=GUI_INTEGER(Charwin,20,172,"EQ-5",0,0,255,1,"")
Global int_eq6.GADGET=GUI_INTEGER(Charwin,20,196,"EQ-6",0,0,255,1,"")

Global int_ceq1.GADGET=GUI_INTEGER(charwin,80,76,"+",0,0,100,1,"")
Global int_cbp1.GADGET=GUI_INTEGER(charwin,221,76,"+",0,0,100,1,"")
Global int_ceq2.GADGET=GUI_INTEGER(charwin,80,100,"+",0,0,100,1,"")
Global int_cbp2.GADGET=GUI_INTEGER(charwin,221,100,"+",0,0,100,1,"")
Global int_ceq3.GADGET=GUI_INTEGER(charwin,80,124,"+",0,0,100,1,"")
Global int_cbp3.GADGET=GUI_INTEGER(charwin,221,124,"+",0,0,100,1,"")
Global int_ceq4.GADGET=GUI_INTEGER(charwin,80,148,"+",0,0,100,1,"")
Global int_cbp4.GADGET=GUI_INTEGER(charwin,221,148,"+",0,0,100,1,"")
Global int_ceq5.GADGET=GUI_INTEGER(charwin,80,172,"+",0,0,100,1,"")
Global int_cbp5.GADGET=GUI_INTEGER(charwin,221,172,"+",0,0,100,1,"")
Global int_ceq6.GADGET=GUI_INTEGER(charwin,80,196,"+",0,0,100,1,"")
Global int_cbp6.GADGET=GUI_INTEGER(charwin,221,196,"+",0,0,100,1,"")



Global frm_c5.GADGET=GUI_FRAME(charwin,12,68,128,192,"Equiped",0,"")
Global frm_c6.GADGET=GUI_FRAME(charwin,148,68,128,192,"Backpack",0,"")

GUI_GAD_TO_TAB(sel_cclass,tab_cchar)
GUI_GAD_TO_TAB(sel_crace,tab_cchar)
GUI_GAD_TO_TAB(sel_ctown,tab_cchar)
GUI_GAD_TO_TAB(sel_csex,tab_cchar)
GUI_GAD_TO_TAB(sel_calign,tab_cchar)
GUI_GAD_TO_TAB(bol_cplus,tab_cchar)
GUI_GAD_TO_TAB(sel_cskill1,tab_cchar)
GUI_GAD_TO_TAB(sel_cskill2,tab_cchar)
GUI_GAD_TO_TAB(frm_c3,tab_cchar)
GUI_GAD_TO_TAB(frm_c4,tab_cchar)
GUI_GAD_TO_TAB(bol_cgc,tab_cchar)
GUI_GAD_TO_TAB(bol_cyc,tab_cchar)
GUI_GAD_TO_TAB(bol_crc,tab_cchar)
GUI_GAD_TO_TAB(bol_cbc,tab_cchar)
GUI_GAD_TO_TAB(txt_cgems,tab_cchar)
GUI_GAD_TO_TAB(txt_cgold,tab_cchar)
GUI_GAD_TO_TAB(int_cfood,tab_cchar)

GUI_GAD_TO_TAB(int_clvl,tab_cstat)
GUI_GAD_TO_TAB(int_cmgt,tab_cstat)
GUI_GAD_TO_TAB(int_cint,tab_cstat)
GUI_GAD_TO_TAB(int_cper,tab_cstat)
GUI_GAD_TO_TAB(int_cend,tab_cstat)
GUI_GAD_TO_TAB(int_cspd,tab_cstat)
GUI_GAD_TO_TAB(int_cacc,tab_cstat)
GUI_GAD_TO_TAB(int_cluc,tab_cstat)
GUI_GAD_TO_TAB(int_cac,tab_cstat)
GUI_GAD_TO_TAB(int_csl,tab_cstat)
GUI_GAD_TO_TAB(txt_cexp,tab_cstat)
GUI_GAD_TO_TAB(int_cage,tab_cstat)
GUI_GAD_TO_TAB(int_cthv,tab_cstat)
GUI_GAD_TO_TAB(txt_chp,tab_cstat)
GUI_GAD_TO_TAB(txt_cmhp,tab_cstat)
GUI_GAD_TO_TAB(txt_csp,tab_cstat)
GUI_GAD_TO_TAB(txt_cmsp,tab_cstat)

gUI_GAD_TO_TAB(int_bp1,tab_citem)
gUI_GAD_TO_TAB(int_bp2,tab_citem)
gUI_GAD_TO_TAB(int_bp3,tab_citem)
gUI_GAD_TO_TAB(int_bp4,tab_citem)
gUI_GAD_TO_TAB(int_bp5,tab_citem)
gUI_GAD_TO_TAB(int_bp6,tab_citem)


gUI_GAD_TO_TAB(int_EQ1,tab_citem)
gUI_GAD_TO_TAB(int_EQ2,tab_citem)
gUI_GAD_TO_TAB(int_EQ3,tab_citem)
gUI_GAD_TO_TAB(int_EQ4,tab_citem)
gUI_GAD_TO_TAB(int_EQ5,tab_citem)
gUI_GAD_TO_TAB(int_EQ6,tab_citem)

;gUI_GAD_TO_TAB(sel_ceq1,tab_citem)
;uI_GAD_TO_TAB(int_ceq1,tab_citem)
;GUI_GAD_TO_TAB(sel_cbp1,tab_citem)
GuI_GAD_TO_TAB(int_ceq1,tab_citem)
GUI_GAD_TO_TAB(int_cbp1,tab_citem)
GuI_GAD_TO_TAB(int_ceq2,tab_citem)
GUI_GAD_TO_TAB(int_cbp2,tab_citem)
GuI_GAD_TO_TAB(int_ceq3,tab_citem)
GUI_GAD_TO_TAB(int_cbp3,tab_citem)
GuI_GAD_TO_TAB(int_ceq4,tab_citem)
GUI_GAD_TO_TAB(int_cbp4,tab_citem)
GuI_GAD_TO_TAB(int_ceq5,tab_citem)
GUI_GAD_TO_TAB(int_cbp5,tab_citem)
GuI_GAD_TO_TAB(int_ceq6,tab_citem)
GUI_GAD_TO_TAB(int_cbp6,tab_citem)


GUI_GAD_TO_TAB(frm_c5,tab_citem)
GUI_GAD_TO_TAB(frm_c6,tab_citem)

gui_settab(charwin,tab_cchar)


 gui_openwin(about)
gui_hidewin(about)
GUI_OPENWIN(Item_win)
gui_hidewin(item_win)
 gui_openwin(monst)
gui_hidewin(monst)
gui_openwin(charwin)
gui_hidewin(charwin)
gui_openwin(mapedit)
gui_hidewin(mapedit)
GUI_OPENWIN(MAIN)
load_map()
				get_map(5)
				unget_map(5)
				get_map(0)
Repeat
	Flip:SetBuffer BackBuffer():Cls
GUI()
	Select EVENT$
		Case "GUI_GADHIT"		;GADGET CLICKED
			Select True
				Case GUI_GADHIT=cmd_items
 				itemed()
				Case GUI_GADHIT=cmd_monsters
 				monstered()
				Case GUI_GADHIT=cmd_char
 				chared()
				Case GUI_GADHIT=cmd_maps
 				map_editor()
				Case GUI_GADHIT=cmd_attrib
  
				Case GUI_GADHIT=cmd_quit
					quit=True
 
				Case GUI_GADHIT=cmd_About		
				gui_showwin(about)
				gui_winfront(About)
				gui_activewin=about
				Repeat
					Flip:SetBuffer BackBuffer():Cls
					GUI()
					Select EVENT$
					Case "GUI_GADHIT"		;GADGET CLICKED
						Select True
							Case gui_gadhit=cmd_ok
							Exit
						End Select
					End Select
					Forever
 					gui_hidewin(about)
					gui_activewin=MAIN

 
			End Select
		Case "MENU"		;MENU HIT
			Select GUI_MENUHIT
 
			End Select
		Case "QMENU"		;QMENU HIT
			Select GUI_QMENUHIT
 
			End Select
		Case "KILLWIN"		;CLOSE WINDOW
			QUIT=True
	End Select
Until QUIT=True
GUI_END()
End



; Item Editor Code Start 
Function Itemed()
	gui_showwin(ITEM_WIN)
	gui_winfront(item_win)
	gui_activewin=item_win
	back=False
Repeat
	Flip:SetBuffer BackBuffer():Cls
GUI()
	Select EVENT$
		Case "GUI_GADHIT"		;GADGET CLICKED
			Select True
				Case GUI_GADHIT=cmd_iload
 					fn$=GUI_FILEREQUEST$("Choose Item.Dat to Load",".dat#All Files#")
					DebugLog fn$
					If fn$<>"" 	
					gui_winactive(Item_win,1)
					load_item(fn$)
					gui_refresh(Item_win)
					
					EndIf
				Case GUI_GADHIT=cmd_isave
 					fn$=GUI_FILEREQUEST$("Choose Item.Dat to Save",".dat#All Files#")
					If fn$<>"" 	
					unfill_values(Int(tm$))
					SAVE_item(fn$)			
					EndIf		
				Case GUI_GADHIT=cmd_about
				
				gui_showwin(about)
				gui_winfront(About)
				gui_activewin=about
				Repeat
					Flip:SetBuffer BackBuffer():Cls
					GUI()
					Select EVENT$
					Case "GUI_GADHIT"		;GADGET CLICKED
						Select True
							Case gui_gadhit=cmd_ok
							Exit
						End Select
					End Select
					Forever
 					gui_hidewin(about)
					gui_activewin=Item_win

				Case GUI_GADHIT=cmd_iback
 				back=True
				Case GUI_GADHIT=sel_item
				unfill_values(tm$)
				temp$=gui_gadtext$(sel_item)
				tm$=""
				For a=1 To 4
				If Not Mid(temp$,a,1)=":"
				tm$=tm$+Mid(temp$,a,1)
				Else 
				Exit
				EndIf
				
				Next
				fill_values(Int(tm$))
				gui_refresh(Item_win)
			End Select
		Case "MENU"		;MENU HIT
			Select GUI_MENUHIT
 
			End Select
		Case "QMENU"		;QMENU HIT
			Select GUI_QMENUHIT
 
			End Select
		Case "KILLWIN"		;CLOSE WINDOW
			back=True
	End Select
Until back=True
gui_hidewin(item_win)
gui_activewin=MAIN
End Function

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
DebugLog "bonus: "+item\Bonus[0]+","+item\bonus[1]
DebugLog "Effect: "+item\effect[0]+","+item\effect[1]
item\damage=ReadByte(filein)
ReadByte(filein)
item\value=ReadShort(filein)
Next
Flip:SetBuffer BackBuffer():Cls
gui_settext(sel_item,as$)
fill_values(0)
CloseFile(filein)
End Function



Function fill_values(no)
	For item.item=Each item
	If item\id=no
	u$=Bin(item\usableby)
	nm=25
	For lp=0 To 7
	item\usa[lp]=Int(Mid(u$,(nm+lp),1))

	Next

	gui_setval(bol_k,item\usa[0])
	gui_setval(bol_p,item\usa[1])
	gui_setval(bol_a,item\usa[2])
	gui_setval(bol_c,item\usa[3])
	gui_setval(bol_s,item\usa[4])
	gui_setval(bol_r,item\usa[5])
	gui_setval(bol_n,item\usa[6])
	gui_setval(bol_b,item\usa[7])
	
	gui_settext(txt_name, item\name$)
	;temp=hex2int(item\effect[0])
	gui_setval(sel_eft,item\effect[0])
	;temp=hex2int(item\effect[1])
	gui_setval(int_eft,item\effect[1])
	;temp=hex2int(item\bonus[0])
	gui_setval(sel_bns,item\bonus[0])
	;temp=hex2int(item\bonus[1])
	gui_setval(int_bns,item\bonus[1])
	gui_setval(int_dmg,item\damage)
	gui_setText(txt_val,Str(item\value))

	End If
	Next
End Function

Function unfill_values(no)
	For item.item=Each item
	If item\id=no
	item\usa[0]=gui_gadval(bol_k)
	item\usa[1]=gui_gadval(bol_p)
	item\usa[2]=gui_gadval(bol_a)
	item\usa[3]=gui_gadval(bol_c)
	item\usa[4]=gui_gadval(bol_s)
	item\usa[5]=gui_gadval(bol_r)
	item\usa[6]=gui_gadval(bol_n)
	item\usa[7]=gui_gadval(bol_b)
	qip=0
	mint$=""
	b=7
	For a=1 To 8
	qip=qip+Int(item\usa[(a-1)])*(2^b)	
	mint$=mint$+item\usa[(a-1)]
	b=b-1
	Next
	item\usableby=qip
	item\name$=Left(gui_gadtext(txt_name),12)
	;temp$=Int2hex(Int(gui_gadval(sel_eft)))
	item\effect[0]=Int(gui_gadval(sel_eft))
	;temp$=Int2hex(Int(gui_gadval(int_eft)))
	item\effect[1]=Int(gui_gadval(int_eft))
	;temp$=Int2hex(Int(gui_gadval(sel_bns)))
	item\bonus[0]=Int(gui_gadval(sel_bns))
	;temp$=Int2hex(Int(gui_gadval(int_bns)))
	item\bonus[1]=Int(gui_gadval(int_bns))
;	gui_setval(sel_eft,temp)
;	temp=hex2int(item\effect[1])
;	gui_setval(int_eft,temp)
;	temp=hex2int(item\bonus[0])
;	gui_setval(sel_bns,temp)
;	temp=hex2int(item\bonus[1])
	gui_setval(int_bns,temp)
	item\damage=gui_gadval(int_dmg)
	item\value=Int(Left(gui_gadval(txt_val),5))
	End If
	Next
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

Function int2hex(in)
	DebugLog "IN:"+in
	Select in
	Case 0
	temp$="0"
	Case 1
	temp$="1"
	Case 2
		temp$="2"
	Case 3
		temp$="3"
	Case 4
		temp$="4"
	Case 5
		temp$="5"
	Case 6
		temp$="6"
	Case 7
		temp$="7"
	Case 8
		temp$="8"
	Case 9
		temp$="9"
	Case 10
		temp$="A"
	Case 11
		temp$="B"
	Case 12
		temp$="C"
	Case 13
		temp$="D"
	Case 14
		temp$="E"
	Case 15
		temp$="F"
End Select
Return temp$

End Function

Function save_item(file$)
fileout=WriteFile(file$)
For item.item=Each item
For c=1 To 12
ch$=Mid(item\name$,c,1)
WriteByte(fileout,Asc(ch$))
Next
WriteByte(fileout,0)
as$=as$+a+": "+item\name$+"#"
WriteByte(fileout,item\usableby)
DebugLog Int(item\bonus[1])+"<"+Int(item\bonus[0])
temp=Int(item\bonus[1])+Int(item\bonus[0])*(16)
DebugLog temp
WriteByte(fileout,temp)
temp=Int(item\effect[1])+Int(item\effect[0])*(16)
;DebugLog temp
WriteByte(fileout,temp)
WriteByte(fileout,item\damage)
WriteByte(fileout,0)
WriteShort(fileout,item\value)
Next
CloseFile(fileout)
End Function

; Item editor code End


; monster editor code start
Function load_monster(file$)
fin=ReadFile(file$)
For a=0 To 255

	mon.monster=New monster
	mon\id=a
	
	; update progress bar :)
;	gui_setval(prg,a)
;	Flip:SetBuffer BackBuffer():Cls
;	GUI()
	; read name and convert to standard letters ie: add 20 to the value read in :)
	For n=0 To 13
	;c$=Chr((ReadByte(fin)+20))
	;DebugLog c$
	temp=ReadByte(fin)
	ch$=Chr(temp-128)
	mon\name$=mon\name$+ch$
	Next 
	;DebugLog	mon\ID+":"+mon\name$
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
	DebugLog mon\ID+":"
	DebugLog mon\name$+" HP:"+(mon\hp)+"("+temp+") Exp:"+mon\exper+"("+tempe+") gold:"+Mid(Right(Hex(mon\gold),2),1,1)+" Gems:"+Right(Hex(mon\gold),1)+" MAX damage: "+Mon\damage
	DebugLog "Undead: "+mon\undead+" Archer: "+mon\Archer+" Can't Run: "+mon\flee+" Picture:"+mon\picture+" AC:"+mon\ac+" SP: "+mon\sp+" MRES:"+mon\mres
	DebugLog "single ability "+mon\abil1+":"+adesc$(mon\abil1)
	If Mid(Right(Hex(mon\pabil),2),1,1)>0 DebugLog "Party  ability "+mon\abil2+":"+partydec$(mon\abil2)

	mon\Colour=clr
	clr=clr+1
	If clr=3
	clr=0
	EndIf
		
Next
CloseFile(fin)
fillmonst(0)
End Function 
Function load_amonster(file$)
fin=ReadFile(file$)
clr=0
For a=0 To 255
	
	mon.monster=New monster
	mon\id=a
	
	; update progress bar :)
;	gui_setval(prg,a)
;	Flip:SetBuffer BackBuffer():Cls
;	GUI()
	; read name and convert to standard letters ie: add 20 to the value read in :)
	For n=0 To 13
	;c$=Chr((ReadByte(fin)+20))
	;DebugLog c$
	temp=ReadByte(fin)
	ch$=Chr(temp-128)
	mon\name$=mon\name$+ch$
	Next 
	;DebugLog	mon\ID+":"+mon\name$
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
	mon\gold=hex2int(Mid(Right(Hex(gold),2),1,1))
	mon\Gems=hex2int(Right(Hex(gold),1))
	mon\damage=ReadByte(fin)
;	mon\abils=ReadByte(fin)
;	mon\abils2=ReadByte(fin)
	mon\speed=ReadByte(fin)
	mon\picture=ReadByte(fin)
	mon\AC=ReadByte(fin)
;	mon\abils3=ReadByte(fin)
	mon\SP=ReadByte(fin)
	mon\MRES=ReadByte(fin)
	

	For ab=1 To 8
;	tempa(ab-1)=Mid(Right(Bin(mon\abils),8),ab,1)
;	tempb(ab-1)=Mid(Right(Bin(mon\abils2),8),ab,1)
;	tempc(ab-1)=Mid(Right(Bin(mon\abils3),8),ab,1)

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
;	mon\ablility1=tempo
	DebugLog mon\ID+":"
	DebugLog mon\name$+" HP:"+(mon\hp)+"("+temp+") Exp:"+mon\exper+"("+tempe+") gold:"+Mid(Right(Hex(mon\gold),2),1,1)+" Gems:"+Right(Hex(mon\gold),1)+" MAX damage: "+Mon\damage
	DebugLog "Undead: "+mon\undead+" Archer: "+mon\Archer+" Can't Run: "+mon\flee+" Picture:"+mon\picture+" AC:"+mon\ac+" SP: "+mon\sp+" MRES:"+mon\mres
	DebugLog "ability "+tempo+":"+adesc$(tempo)
	mon\Colour=clr
	clr=clr+1
	If clr=3
	clr=0
	EndIf
		
Next
CloseFile(fin)
fillmonst(0)
End Function


Function monstered()
	gui_showwin(monst)
	gui_winfront(monst)
	gui_activewin=monst
	back=False
Repeat
	Flip:SetBuffer BackBuffer():Cls
GUI()
	Select EVENT$
		Case "GUI_GADHIT"		;GADGET CLICKED
			Select True
				Case GUI_GADHIT=cmd_mload
 					fn$=GUI_FILEREQUEST$("Choose monster.Dat to Load",".dat#All Files#")
					DebugLog fn$
					If fn$<>"" 	
					load_monster(fn$)
					EndIf
				Case GUI_GADHIT=cmd_msave
 					fn$=GUI_FILEREQUEST$("Choose monster.Dat to Save",".dat#All Files#")
					If fn$<>"" 	
					EndIf		
				Case GUI_GADHIT=cmd_mabout
				
				gui_showwin(about)
				gui_winfront(About)
				gui_activewin=about
				Repeat
					Flip:SetBuffer BackBuffer():Cls
					GUI()
					Select EVENT$
					Case "GUI_GADHIT"		;GADGET CLICKED
						Select True
							Case gui_gadhit=cmd_ok
							Exit
						End Select
					End Select
					Forever
 					gui_hidewin(about)
					gui_activewin=monst
				Case GUI_GADHIT=cmd_mprev
				mon_no=mon_no-1
				If mon_no<0 Then mon_no=255
				fillmonst(mon_no)
				Case GUI_GADHIT=cmd_mnext
				mon_no=mon_no+1
				If mon_no>255 Then mon_no=0
				fillmonst(mon_no)
				Case GUI_GADHIT=cmd_mback
 				back=True
			End Select
		Case "MENU"		;MENU HIT
			Select GUI_MENUHIT
 
			End Select
		Case "QMENU"		;QMENU HIT
			Select GUI_QMENUHIT
 
			End Select
		Case "KILLWIN"		;CLOSE WINDOW
			back=True
	End Select
Until back=True
gui_hidewin(monst)
gui_activewin=MAIN
End Function


Function fillmonst(no)
	For mon.monster=Each monster
		If mon\id=no
			gui_settext(txt_mname,mon\name$)
			temp=mon\ohp
			gui_setval(sld_mhp,temp)
			If temp<71
				gui_settext(msg_m3,"HP: "+(temp+1))
			Else If temp>70 And temp<134
						gui_settext(msg_m3,"HP: "+(70+((temp-70)*10)))
			Else If temp>133 And temp<178
				gui_settext(msg_m3,"HP: "+(700+((temp-134)*100)))
			Else If temp>177 
				gui_settext(msg_m3,"HP: "+(64000-(255-temp)*1000))
			End If
	
			; Experience similar in workings to hp but able to use bigger values.
			tempe=mon\oexp
			gui_setval(sld_mexp,tempe)
			If tempe<62
			gui_settext(msg_m4,"EXP: "+(60+((tempe-37)*10)))
			Else If tempe<128
				gui_settext(msg_m4,"EXP: "+(200+((tempe-65)*100)))
			Else If tempe<138
				gui_settext(msg_m4,"EXP: "+(1000+((tempe-128)*1000)))
			Else If tempe<160
				gui_settext(msg_m4,"EXP: "+(10000+((tempe-137)*1000)))
			Else If tempe<190
				gui_settext(msg_m4,"EXP: "+(10000+((tempe-160)*10000)))
			Else If tempe<220
				gui_settext(msg_m4,"EXP: "+(0000+((tempe-191)*10000)))
			Else
				gui_settext(msg_m4,"EXP: "+(10000+((tempe-224)*10000)))
			EndIf

			gui_setval(int_mac,mon\AC)
			gui_setval(int_mres,mon\MRES)
			gui_setval(int_mgold,mon\gold)
			gui_setval(int_mac,mon\gems)
			gui_setval(sel_mat1,mon\abil1)
			gui_setval(bol_mundead,mon\undead)
			gui_setval(bol_marcher,mon\archer)
			If Mid(Right(Hex(mon\pabil),2),1,1)>0
				gui_setval(SEL_mpab,mon\abil2)
				gui_setval(sel_mpaf,hex2int(Mid(Right(Hex(mon\pabil),2),1,1)))
				DebugLog Right(Hex(mon\pabil),2)
			Else
				gui_setval(SEL_mpab,33)
				gui_setval(sel_mpaf,Mid(Right(Hex(mon\pabil),2),1,1))
			EndIf
			
			gui_refresh(Monst)

		EndIf
	Next
End Function
;monster editor code end



; roster editor code start

Function chared()
	gui_showwin(charwin)
	gui_winfront(charwin)
	gui_activewin=charwin
	back=False
Repeat
	Flip:SetBuffer BackBuffer():Cls
GUI()
	Select EVENT$
		Case "GUI_GADHIT"		;GADGET CLICKED
			Select True
				Case GUI_GADHIT=cmd_cload
 					fn$=GUI_FILEREQUEST$("Choose roster.Dat to Load",".dat#All Files#")
					DebugLog fn$
					If fn$<>"" 	
					load_Roster(fn$)
					EndIf
				Case GUI_GADHIT=cmd_csave
 					fn$=GUI_FILEREQUEST$("Choose roster.Dat to Save",".dat#All Files#")
					If fn$<>"" 	
					;unfillchar(char_no)
					save_roster(fn$)
					EndIf		
				Case gui_gadhit=sel_ctype
					fillchar(char_no)
				Case GUI_GADHIT=cmd_cabout
				
				gui_showwin(about)
				gui_winfront(About)
				gui_activewin=about
				Repeat
					Flip:SetBuffer BackBuffer():Cls
					GUI()
					Select EVENT$
					Case "GUI_GADHIT"		;GADGET CLICKED
						Select True
							Case gui_gadhit=cmd_ok
							Exit
						End Select
					End Select
					Forever
 					gui_hidewin(about)
					gui_activewin=charwin
				Case GUI_GADHIT=cmd_cprev
				unfillchar(char_no)
				char_no=char_no-1
				If char_no<1 Then char_no=24
				fillchar(char_no)
				Case GUI_GADHIT=cmd_cnext
				unfillchar(char_no)
				char_no=char_no+1
				If char_no>24 Then char_no=1
				fillchar(char_no)
				Case GUI_GADHIT=cmd_cback
 				back=True
			End Select
		Case "MENU"		;MENU HIT
			Select GUI_MENUHIT
 
			End Select
		Case "QMENU"		;QMENU HIT
			Select GUI_QMENUHIT
 
			End Select
		Case "KILLWIN"		;CLOSE WINDOW
			back=True
	End Select
Until back=True
gui_hidewin(charwin)
gui_activewin=MAIN

End Function
Function load_roster(file$)
fin=ReadFile(file$)
For a=1 To 24
	DebugLog "character:"+a
	char.character=New character
	char\ID=a
	For c=0 To 9
		ch$=Chr(ReadByte(fin))
		char\name$=char\name$+ch
	Next
	
	DebugLog "NAME:"+char\name$
	ReadByte(fin)
	char\town=ReadByte(fin)
	
	;debuglog "Town: "+town(char\town)
	char\sex=ReadByte(fin)
	If char\sex=0
	;debuglog "sex: Male"
	Else
	;debuglog "sex: female"
	End If
	char\allignment=ReadByte(fin)

	Select 	char\allignment

	Case 0
	;debuglog "Allignment: Good"
	Case 1
	;debuglog "Allignment: Neutral"
	Case 2
		;debuglog "Allignment: Evil"
	End Select
	char\race=ReadByte(fin)
	;debuglog "Race: "+race(char\race)
	char\class=ReadByte(fin)
	;debuglog "Class: "+class(char\class)
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
		
	;debuglog "Might:"+char\mgt
		;debuglog "Intellect:"+char\inte
		;debuglog "Personality:"+char\per
		;debuglog "Endurance:"+char\endu
		;debuglog "Speed:"+char\spd
		;debuglog "Accuracy:"+char\acc
		;debuglog "Luck:"+char\lck
		
		SeekFile(fin,(((a-1)*130)+30))
		char\thv=ReadByte(fin)
		;debuglog "Thievery: "+char\thv
		char\lvl=ReadByte(fin)
		char\lvl=ReadByte(fin)
		;debuglog "Level: "+char\lvl
		char\age=ReadByte(fin)
		;debuglog "Age: "+char\age
		char\day=ReadByte(fin)
		;debuglog "Days old: "+char\day
		char\sPLVL=ReadByte(fin)
		;debuglog "Spell Level:"+char\splvl
		char\food=ReadByte(fin)
		char\food=ReadByte(fin)
		;debuglog "Food:"+char\food
		ReadByte(fin)	
		ReadByte(fin)	
		For eq=0 To 5
			char\equiped[eq]=ReadByte(fin)		
		Next
		
		For eq=0 To 5
			char\equiefft[eq]=ReadByte(fin)		
		Next
		For eq=0 To 5
			char\equiplus[eq]=ReadByte(fin)		
		Next
	For eq=0 To 5
		For item.item=Each item
			If char\equiped[eq]=item\id
				;debuglog "Equiped:"+item\name$+"+"+char\equiplus[eq]
			End If
		Next
	Next
			For eq=0 To 5
			char\backpack[eq]=ReadByte(fin)		
		Next
		For eq=0 To 5
			char\backefft[eq]=ReadByte(fin)		
		Next
		For eq=0 To 5
			char\backplus[eq]=ReadByte(fin)		
		Next
		
	For eq=0 To 5
		For item.item=Each item
			If char\backpack[eq]=item\id
				;debuglog "Backpack:"+item\name$+"+"+char\backplus[eq]
			End If
		Next
	Next
		SeekFile(fin,((a-1)*130)+80)
		temp$=Hex(ReadByte(fin))
		
		char\skill[0]=hex2int(Mid(Right(temp$,2),1,1))
		char\skill[1]=hex2int(Right(temp$,1))

		;debuglog "Skill 1: "+skill(char\skill[0])
		;debuglog "Skill 2: "+skill(char\skill[1])
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
		;debuglog "Has Spells"
		as$=""
		For spl=1 To 7

		as$=as$+"1-"+Str$(spl)+"(
		as$=as$+char\spell[spl2]+") "
		spl2=spl2+1
		Next
		;debuglog as$
	as$=""

		For spl=1 To 7
		as$=as$+"2-"+Str$(spl)+"(
		as$=as$+char\spell[spl2]+") "
		spl2=spl2+1
	
		Next
			;debuglog as$	
	as$=""

		For spl=1 To 6
			as$=as$+"3-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
			
				
		Next
				;debuglog as$
	as$=""

		For spl=1 To 6
			as$=as$+"4-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;debuglog as$
	as$=""

		For spl=1 To 5
			as$=as$+"5-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;debuglog as$
	as$=""

		For spl=1 To 5
			as$=as$+"6-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"7-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
		Next
				;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"8-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
		Next
				;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"9-"+Str$(spl)+"(
			as$=as$+char\spell[spl2]+") "
			spl2=spl2+1
		Next
				;debuglog as$
		SeekFile(fin,((a-1)*130)+88)
		char\SP=ReadShort(fin)
		char\MSP=ReadShort(fin)
		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		DebugLog "GEMS:"+temp1+" "+temp2
		char\GEMS=temp1+(temp2*256)
		char\HP=ReadShort(fin)
		char\MHP=ReadShort(fin)
		;debuglog "HIT POINTS "+char\Hp+"/"+char\MHp
		;debuglog "SPELL POINTS "+char\sp+"/"+char\Msp

		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
		temp4=ReadByte(fin)
			char\exper=temp1+(temp2*256)+(temp3*65536)+(temp4*16777216)
temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
			char\GOLD=temp1+(temp2*256)+(temp3*65536)

;		;debuglog Right(Hex(temp1),2)+" "+Right(Hex(temp2),2)+" "+Right(Hex(temp3),2)+" "+Right(Hex(temp4),2)+" "
		;debuglog "Experience:"+char\exper
	;debuglog "Gold:"+char\gold		
		;debuglog "Gems:"+char\gems
		SeekFile(fin,((a-1)*130)+121)
		char\plus=ReadByte(fin)
		;debuglog "Has Plus:"+Right(Bin(char\plus),8)
		SeekFile(fin,((a)*130))
;	For b=0 To 107
;	temp=ReadByte(fin)
;	;debuglog 	Chr(temp)
;	Next
	;debuglog "End Of character:"+a
	;debuglog ""

Next

For a=1 To 24
	;debuglog "Hireling:"+a
	hire.hireling=New hireling
	hire\ID=a
	For c=0 To 9
		ch$=Chr(ReadByte(fin))
		hire\name$=hire\name$+ch
	Next
	
	;debuglog "NAME:"+hire\name$
	ReadByte(fin)
	hire\town=ReadByte(fin)
	
	;debuglog "Town: "+town(hire\town)
	hire\sex=ReadByte(fin)
	If hire\sex=0
	;debuglog "sex: Male"
	Else
	;debuglog "sex: female"
	End If
	hire\allignment=ReadByte(fin)

	Select 	hire\allignment

	Case 0
	;debuglog "Allignment: Good"
	Case 1
	;debuglog "Allignment: Neutral"
	Case 2
		;debuglog "Allignment: Evil"
	End Select
	hire\race=ReadByte(fin)
	;debuglog "Race: "+race(hire\race)
	hire\class=ReadByte(fin)
	;debuglog "Class: "+class(hire\class)
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
		
	;debuglog "Might:"+hire\mgt
		;debuglog "Intellect:"+hire\inte
		;debuglog "Personality:"+hire\per
		;debuglog "Endurance:"+hire\endu
		;debuglog "Speed:"+hire\spd
		;debuglog "Accuracy:"+hire\acc
		;debuglog "Luck:"+hire\lck
		
		SeekFile(fin,((((a+24)-1)*130)+30))
		hire\thv=ReadByte(fin)
		;debuglog "Thievery: "+hire\thv
		hire\lvl=ReadByte(fin)
		hire\lvl=ReadByte(fin)
		;debuglog "Level: "+hire\lvl
		hire\age=ReadByte(fin)
		;debuglog "Age: "+hire\age
		hire\day=ReadByte(fin)
		;debuglog "Days old: "+hire\day
		hire\sPLVL=ReadByte(fin)
		;debuglog "Spell Level:"+hire\splvl
		hire\food=ReadByte(fin)
		hire\food=ReadByte(fin)
		;debuglog "Food:"+hire\food
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
				;debuglog "Equiped:"+item\name$+"+"+hire\equiplus[eq]
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
				;debuglog "Backpack:"+item\name$+"+"+hire\backplus[eq]
			End If
		Next
	Next
		SeekFile(fin,(((a+24)-1)*130)+80)
		temp$=Hex(ReadByte(fin))
		
		hire\skill[0]=hex2int(Mid(Right(temp$,2),1,1))
		hire\skill[1]=hex2int(Right(temp$,1))
		
				;debuglog "OFFSET:"+FilePos(fin)
		;debuglog "Skill 1: "+skill(hire\skill[0])
		;debuglog "Skill 2: "+skill(hire\skill[1])
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
		;debuglog "Has Spells"
		as$=""
		For spl=1 To 7

		as$=as$+"1-"+Str$(spl)+"(
		as$=as$+hire\spell[spl2]+") "
		spl2=spl2+1
		Next
		;debuglog as$
	as$=""

		For spl=1 To 7
		as$=as$+"2-"+Str$(spl)+"(
		as$=as$+hire\spell[spl2]+") "
		spl2=spl2+1
	
		Next
			;debuglog as$	
	as$=""

		For spl=1 To 6
			as$=as$+"3-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
				
		Next
				;debuglog as$
	as$=""

		For spl=1 To 6
			as$=as$+"4-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;debuglog as$
	as$=""

		For spl=1 To 5
			as$=as$+"5-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;debuglog as$
	as$=""

		For spl=1 To 5
			as$=as$+"6-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"7-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
		Next
				;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"8-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
		Next
				;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"9-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
		Next
				;debuglog as$
		SeekFile(fin,(((a+24)-1)*130)+88)
		hire\SP=ReadShort(fin)
		hire\MSP=ReadShort(fin)
		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		hire\GEMS=temp1+(temp2*256)
		hire\HP=ReadShort(fin)
		hire\MHP=ReadShort(fin)
		;debuglog "HIT POINTS "+hire\Hp+"/"+hire\MHp
		;debuglog "SPELL POINTS "+hire\sp+"/"+hire\Msp

		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
		temp4=ReadByte(fin)
			hire\exper=temp1+(temp2*256)+(temp3*65536)+(temp4*16777216)
temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
			hire\cost=temp1+(temp2*256)+(temp3*65536)

;		;debuglog Right(Hex(temp1),2)+" "+Right(Hex(temp2),2)+" "+Right(Hex(temp3),2)+" "+Right(Hex(temp4),2)+" "
		;debuglog "Experience:"+hire\exper
	;debuglog "cost:"+hire\cost	
		;debuglog "Gems:"+hire\gems
		SeekFile(fin,(((a+24)-1)*130)+121)
		hire\plus=ReadByte(fin)
		;debuglog "Has Plus:"+Right(Bin(hire\plus),8)
		SeekFile(fin,((a+24)*130))
;	For b=0 To 107
;	temp=ReadByte(fin)
;	;debuglog 	Chr(temp)
;	Next
	;debuglog "End Of Hireling:"+a
	;debuglog ""

Next


fillchar(1)

End Function

Function fillchar(id)
choice=gui_gadval(sel_ctype)
Select choice
	Case 0 
	For char.character=Each character
	If char\id=ID
	gui_settext(txt_cname,char\name$)
	gui_setval(sel_cclass,char\class)
	gui_setval(sel_crace,char\race)
	gui_setval(sel_ctown,char\town)
	gui_setval(sel_csex,char\sex)
	gui_setval(sel_calign,char\allignment)		
	gui_setval(sel_cskill1,char\skill[0])		
	gui_setval(sel_cskill2,char\skill[1])		
	gui_setval(int_cfood,char\food)
	gui_settext(txt_cgems,Str(char\gems))
	gui_settext(txt_cgold,Str(char\gold))
		gui_setval(int_clvl,char\lvl)
	gui_setval(int_cmgt,char\mgt)
	gui_setval(int_cint,char\inte)
	gui_setval(int_cper,char\per)
	gui_setval(int_cend,char\endu)
	gui_setval(int_cspd,char\spd)
	gui_setval(int_cacc,char\acc)
	gui_setval(int_cluc,char\lck)
	gui_setval(int_cage,char\age)
	gui_setval(int_cac,char\ac)
	gui_setval(int_csl,char\splvl)
	gui_setval(int_cthv,char\thv)
	gui_settext(txt_chp,char\hp)
	gui_settext(txt_csp,char\sp)
	gui_settext(txt_cmhp,char\mhp)
	gui_settext(txt_cmsp,char\msp)
	gui_settext(txt_cexp,char\exper)	
	gui_setval(int_bp1,char\backpack[0])
	gui_setval(int_bp2,char\backpack[1])
	gui_setval(int_bp3,char\backpack[2])
	gui_setval(int_bp4,char\backpack[3])
	gui_setval(int_bp5,char\backpack[4])
	gui_setval(int_bp6,char\backpack[5])
	gui_setval(int_eq1,char\equiped[0])
	gui_setval(int_eq2,char\equiped[1])
	gui_setval(int_eq3,char\equiped[2])
	gui_setval(int_eq4,char\equiped[3])
	gui_setval(int_eq5,char\equiped[4])
	gui_setval(int_eq6,char\equiped[5])
	gui_setval(int_cbp1,char\backplus[0])
	gui_setval(int_cbp2,char\backplus[1])
	gui_setval(int_cbp3,char\backplus[2])
	gui_setval(int_cbp4,char\backplus[3])
	gui_setval(int_cbp5,char\backplus[4])
	gui_setval(int_cbp6,char\backplus[5])
	gui_setval(int_ceq1,char\equiplus[0])
	gui_setval(int_ceq2,char\equiplus[1])
	gui_setval(int_ceq3,char\equiplus[2])
	gui_setval(int_ceq4,char\equiplus[3])
	gui_setval(int_ceq5,char\equiplus[4])
	gui_setval(int_ceq6,char\equiplus[5])





	EndIf
	Next
	Case 1
	For hire.hireling=Each hireling
	If hire\id=ID
	gui_settext(txt_cname,hire\name$)
	gui_setval(sel_cclass,hire\class)
	gui_setval(sel_crace,hire\race)
	gui_setval(sel_ctown,hire\town)
	gui_setval(sel_csex,hire\sex)
	gui_setval(sel_calign,hire\allignment)		
	gui_setval(sel_cskill1,hire\skill[0])		
	gui_setval(sel_cskill2,hire\skill[1])		
	gui_setval(int_cfood,hire\food)
	gui_settext(txt_cgems,Str(hire\gems))
	gui_settext(txt_cgold,Str(hire\cost))
	gui_setval(int_clvl,hire\lvl)
	gui_setval(int_cmgt,hire\mgt)
	gui_setval(int_cint,hire\inte)
	gui_setval(int_cper,hire\per)
	gui_setval(int_cend,hire\endu)
	gui_setval(int_cspd,hire\spd)
	gui_setval(int_cacc,hire\acc)
	gui_setval(int_cluc,hire\lck)
	gui_setval(int_cage,hire\age)
	gui_setval(int_cthv,hire\thv)
	gui_setval(int_cac,hire\ac)
	gui_setval(int_csl,hire\splvl)
	gui_settext(txt_chp,hire\hp)
	gui_settext(txt_csp,hire\sp)
	gui_settext(txt_cmhp,hire\mhp)
	gui_settext(txt_cmsp,hire\msp)
	gui_settext(txt_cexp,hire\exper)		

	EndIf
	Next
	End Select	
	gui_refresh(charwin)
End Function

Function unfillchar(id)
choice=gui_gadval(sel_ctype)
Select choice
	Case 0 
	For char.character=Each character
	If char\id=ID
	char\name$=gui_gadtext$(txt_cname)
	char\class=gui_gadval(sel_cclass)
	char\race=gui_gadval(sel_crace)
	char\town=gui_gadval(sel_ctown)
	char\sex=gui_gadval(sel_csex)
	char\allignment=gui_gadval(sel_calign)		
	char\skill[0]=gui_gadval(sel_cskill1)		
	char\skill[1]=gui_gadval(sel_cskill2)			
	char\food=gui_gadval(int_cfood)
	char\gems=Int(gui_gadtext$(txt_cgems))
	char\exper=Int(gui_gadtext$(txt_cexp))
	char\gold=Int(gui_gadtext$(txt_cgold))
	char\lvl=gui_gadval(int_clvl)
	char\mgt=gui_gadval(int_cmgt)
	char\inte=gui_gadval(int_cint)
	char\per=gui_gadval(int_cper)
	char\endu=gui_gadval(int_cend)
	char\spd=gui_gadval(int_cspd)
	char\acc=gui_gadval(int_cacc)
	char\lck=gui_gadval(int_cluc)
	char\thv=gui_gadval(int_cthv)
	char\ac=gui_gadval(int_cac)
	char\age=gui_gadval(int_cage)
	char\splvl=gui_gadval(int_csl)
	char\hp=Int(gui_gadtext$(txt_chp))
	char\mhp=Int(gui_gadtext$(txt_cmhp))
	char\sp=Int(gui_gadtext$(txt_csp))
	char\msp=Int(gui_gadtext$(txt_cmsp))
	char\backpack[0]=gui_gadval(int_bp1)
	char\equiped[0]=gui_gadval(int_eq1)
	char\backpack[1]=gui_gadval(int_bp2)
	char\equiped[1]=gui_gadval(int_eq2)
	char\backpack[2]=gui_gadval(int_bp3)
	char\equiped[2]=gui_gadval(int_eq3)
	char\backpack[3]=gui_gadval(int_bp4)
	char\equiped[3]=gui_gadval(int_eq4)
	char\backpack[4]=gui_gadval(int_bp5)
	char\equiped[4]=gui_gadval(int_eq5)
	char\backpack[5]=gui_gadval(int_bp6)
	char\equiped[5]=gui_gadval(int_eq6)
	char\backplus[0]=gui_gadval(int_cbp1)
	char\equiplus[0]=gui_gadval(int_ceq1)
	char\backplus[1]=gui_gadval(int_cbp2)
	char\equiplus[1]=gui_gadval(int_ceq2)
	char\backplus[2]=gui_gadval(int_cbp3)
	char\equiplus[2]=gui_gadval(int_ceq3)
	char\backplus[3]=gui_gadval(int_cbp4)
	char\equiplus[3]=gui_gadval(int_ceq4)
	char\backplus[4]=gui_gadval(int_cbp5)
	char\equiplus[4]=gui_gadval(int_ceq5)
	char\backplus[5]=gui_gadval(int_cbp6)
	char\equiplus[5]=gui_gadval(int_ceq6)
	
	EndIf
	Next
	Case 1
	For hire.hireling=Each hireling
	If hire\id=ID
	hire\name$=gui_gadtext$(txt_cname)
	hire\class=gui_gadval(sel_cclass)
	hire\race=gui_gadval(sel_crace)
	hire\town=gui_gadval(sel_ctown)
	hire\sex=gui_gadval(sel_csex)
	hire\allignment=gui_gadval(sel_calign)		
	hire\skill[0]=gui_gadval(sel_cskill1)		
	hire\skill[1]=gui_gadval(sel_cskill2)			
	hire\food=gui_gadval(int_cfood)
	hire\gems=Int(gui_gadtext$(txt_cgems))
	hire\cost=Int(gui_gadtext$(txt_cgold))
	hire\lvl=gui_gadval(int_clvl)
	hire\mgt=gui_gadval(int_cmgt)
	hire\inte=gui_gadval(int_cint)
	hire\per=gui_gadval(int_cper)
	hire\endu=gui_gadval(int_cend)
	hire\spd=gui_gadval(int_cspd)
	hire\acc=gui_gadval(int_cacc)
	hire\lck=gui_gadval(int_cluc)
	hire\thv=gui_gadval(int_cthv)
	hire\ac=gui_gadval(int_cac)
	hire\age=gui_gadval(int_cage)
	hire\splvl=gui_gadval(int_csl)
	hire\hp=Int(gui_gadtext$(txt_chp))
	hire\mhp=Int(gui_gadtext$(txt_cmhp))
	hire\sp=Int(gui_gadtext$(txt_csp))
	hire\msp=Int(gui_gadtext$(txt_cmsp))
	EndIf
	Next
	End Select	
	gui_refresh(charwin)
End Function

Function save_roster(file$)
fo=OpenFile(file$)
	For char.character=Each character
	
	SeekFile(fo,(char\id-1)*130)
	For c=1 To 10
		ch$=Mid(char\name$,c,1)
;				DebugLog ch$
		WriteByte(fo,Asc(ch$))
		;Chr(ReadByte(fin))
	Next
	WriteByte(fo,0)
	WriteByte(fo,char\town)
	WriteByte(fo,char\sex)
	WriteByte(fo,char\allignment)
	WriteByte(fo,char\race)
	WriteByte(fo,char\class)
	WriteByte(fo,char\mgt)
	WriteByte(fo,char\inte)
	WriteByte(fo,char\per)
	crloc=FilePos(fo)
	SeekFile(fo,(((char\id-1)*130)+39))
	DebugLog FilePos(fo)
	WriteByte(fo,char\endu)			
	SeekFile(fo,crloc)	
	WriteByte(fo,char\spd)			
	WriteByte(fo,char\acc)
	WriteByte(fo,char\lck)
	SeekFile(fo,(((char\id-1)*130)+30))
	WriteByte(fo,char\thv)
	;WriteByte(fo,0)
	SeekFile(fo,FilePos(fo)+1)	
	WriteByte(fo,char\lvl)
	WriteByte(fo,char\age)
	WriteByte(fo,char\day)
	WriteByte(fo,char\splvl)
;	WriteByte(fo,0)
	SeekFile(fo,FilePos(fo)+1)	
	WriteByte(fo,char\food)
	SeekFile(fo,FilePos(fo)+2)	
;	WriteByte(fo,0)
;	WriteByte(fo,0)
	; start of equipment.
	For eq=0 To 5
		WriteByte(fo,char\equiped[eq])		
	Next
	For eq=0 To 5
		WriteByte(fo,char\equiefft[eq])
	Next
	For eq=0 To 5
		WriteByte(fo,char\equiplus[eq])
	Next
		For eq=0 To 5
		WriteByte(fo,char\backpack[eq])		
	Next
	For eq=0 To 5
		WriteByte(fo,char\backefft[eq])
	Next
	For eq=0 To 5
		WriteByte(fo,char\backplus[eq])
	Next

	SeekFile(fo,((char\id-1)*130)+80)
	temp$=""+int2Hex(char\skill[0])+int2hex(char\skill[1])
	DebugLog temp$
	WriteByte(fo,temp$)
	DebugLog "----------------"
	stemp=0
	For spl=1 To 8
	stemp=stemp+(char\spell[spl]*(2^(spl-1)))
	Next
		DebugLog stemp
	;WriteByte(fo,stemp)
		stemp=0
	For spl=1 To 8
	stemp=stemp+(char\spell[spl+8]*(2^(spl-1)))
	Next
		DebugLog stemp
	;WriteByte(fo,stemp)
	stemp=0
	For spl=1 To 8
	stemp=stemp+(char\spell[spl+16]*(2^(spl-1)))
	Next
	DebugLog stemp
	;	WriteByte(fo,stemp)
	stemp=0
	For spl=1 To 8
	stemp=stemp+(char\spell[spl+24]*(2^(spl-1)))
	Next
		DebugLog stemp
	;WriteByte(fo,stemp)
	stemp=0
	For spl=1 To 8
	stemp=stemp+(char\spell[spl+32]*(2^(spl-1)))
	Next
		DebugLog stemp
	;WriteByte(fo,stemp)	
	stemp=0
	For spl=1 To 8
	stemp=stemp+(char\spell[spl+40]*(2^(spl-1)))
	Next
		DebugLog stemp
	;WriteByte(fo,stemp)
	SeekFile(fo,((char\id-1)*130)+88)
	WriteShort(fo,char\SP)
	WriteShort(fo,char\msp)
	gemshex$=""
	gemshex=Hex(char\gems)
	DebugLog char\name
	DebugLog gemshex
	While Len(gemshex)<4
		gemshex="0"+gemshex
	Wend
	tmp$=(Right(gemshex,4))
	tmp1$=Left(tmp,2)
	tmp3=hex2int(Left(tmp1$,1))
	tmp4=hex2int(Right(tmp1$,1))
	tmp5=(tmp3*16)+tmp4
;	DebugLog tmp3+" "+tmp4+" "+tmp5+"("+Hex(tmp5)+")

	tmp2$=Right(gemshex,2)
	tmp6=hex2int(Left(tmp2$,1))
	tmp7=hex2int(Right(tmp2$,1))
	tmp8=(tmp6*16)+tmp7
;	DebugLog tmp6+" "+tmp7+" "+tmp8+"("+Hex(tmp8)+")

;	DebugLog tmp1+" "+tmp2
	WriteByte(fo,(tmp8))
	WriteByte(fo,(tmp5))
	WriteShort(fo,char\HP)
	WriteShort(fo,char\mHp)
	exphex$=Hex(char\exper)
	DebugLog char\EXPer+" "+exphex
	
	
	etmp1$=Left(exphex,2)
	etmp2$=Right(Left(exphex,4),2)
	etmp3$=Left(Right(exphex,4),2)
	etmp4$=Right(exphex,2)
	DebugLog etmp1$+" "+etmp2$+" "+etmp3$+" "+etmp4$
	
	etmp5=hex2int(Left(etmp4$,1))
	etmp6=hex2int(Right(etmp4$,1))
	etmp7=(etmp5*16)+etmp6
	DebugLog etmp5+" "+etmp6+" "+etmp7+"("+Hex(etmp7)+")
	WriteByte(fo,(etmp7))

	etmp5=hex2int(Left(etmp3$,1))
	etmp6=hex2int(Right(etmp3$,1))
	etmp7=(etmp5*16)+etmp6
	DebugLog etmp5+" "+etmp6+" "+etmp7+"("+Hex(etmp7)+")
	WriteByte(fo,(etmp7))
		
	etmp5=hex2int(Left(etmp2$,1))
	etmp6=hex2int(Right(etmp2$,1))
	etmp7=(etmp5*16)+etmp6
	DebugLog etmp5+" "+etmp6+" "+etmp7+"("+Hex(etmp7)+")
	WriteByte(fo,(etmp7))
		
	etmp5=hex2int(Left(etmp1$,1))
	etmp6=hex2int(Right(etmp1$,1))
	etmp7=(etmp5*16)+etmp6
	DebugLog etmp5+" "+etmp6+" "+etmp7+"("+Hex(etmp7)+")
	WriteByte(fo,(etmp7))
	exphex$=Hex(char\gold)
	DebugLog char\EXPer+" "+exphex
	
	
	etmp1$=Left(exphex,2)
	etmp2$=Right(Left(exphex,4),2)
	etmp3$=Left(Right(exphex,4),2)
	etmp4$=Right(exphex,2)
	DebugLog etmp1$+" "+etmp2$+" "+etmp3$+" "+etmp4$
	
	etmp5=hex2int(Left(etmp4$,1))
	etmp6=hex2int(Right(etmp4$,1))
	etmp7=(etmp5*16)+etmp6
	DebugLog etmp5+" "+etmp6+" "+etmp7+"("+Hex(etmp7)+")
	WriteByte(fo,(etmp7))

	etmp5=hex2int(Left(etmp3$,1))
	etmp6=hex2int(Right(etmp3$,1))
	etmp7=(etmp5*16)+etmp6
	DebugLog etmp5+" "+etmp6+" "+etmp7+"("+Hex(etmp7)+")
	WriteByte(fo,(etmp7))
		
	etmp5=hex2int(Left(etmp2$,1))
	etmp6=hex2int(Right(etmp2$,1))
	etmp7=(etmp5*16)+etmp6
	DebugLog etmp5+" "+etmp6+" "+etmp7+"("+Hex(etmp7)+")
	WriteByte(fo,(etmp7))
		
	etmp5=hex2int(Left(etmp1$,1))
	etmp6=hex2int(Right(etmp1$,1))
	etmp7=(etmp5*16)+etmp6
	;DebugLog etmp5+" "+etmp6+" "+etmp7+"("+Hex(etmp7)+")
	;WriteByte(fo,(etmp7))

	Next

End Function


Function sdgsdg()
If blahblah=342345634
	char.character=First character
	For a=0 To 1

				;debuglog as$
		SeekFile(fin,((a-1)*130)+88)
		char\SP=ReadShort(fin)
		char\MSP=ReadShort(fin)
		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		char\GEMS=temp1+(temp2*256)
		char\HP=ReadShort(fin)
		char\MHP=ReadShort(fin)
		;debuglog "HIT POINTS "+char\Hp+"/"+char\MHp
		;debuglog "SPELL POINTS "+char\sp+"/"+char\Msp

		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
		temp4=ReadByte(fin)
			char\exper=temp1+(temp2*256)+(temp3*65536)+(temp4*16777216)
			
		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
			char\GOLD=temp1+(temp2*256)+(temp3*65536)

;		;debuglog Right(Hex(temp1),2)+" "+Right(Hex(temp2),2)+" "+Right(Hex(temp3),2)+" "+Right(Hex(temp4),2)+" "
		;debuglog "Experience:"+char\exper
	;debuglog "Gold:"+char\gold		
		;debuglog "Gems:"+char\gems
		SeekFile(fin,((a-1)*130)+121)
		char\plus=ReadByte(fin)
		;debuglog "Has Plus:"+Right(Bin(char\plus),8)
		SeekFile(fin,((a)*130))
;	For b=0 To 107
;	temp=ReadByte(fin)
;	;debuglog 	Chr(temp)
;	Next
	;debuglog "End Of character:"+a
	;debuglog ""

Next

For a=1 To 24
	;debuglog "Hireling:"+a
	hire.hireling=New hireling
	hire\ID=a
	For c=0 To 9
		ch$=Chr(ReadByte(fin))
		hire\name$=hire\name$+ch
	Next
	
	;debuglog "NAME:"+hire\name$
	ReadByte(fin)
	hire\town=ReadByte(fin)
	
	;debuglog "Town: "+town(hire\town)
	hire\sex=ReadByte(fin)
	If hire\sex=0
	;debuglog "sex: Male"
	Else
	;debuglog "sex: female"
	End If
	hire\allignment=ReadByte(fin)

	Select 	hire\allignment

	Case 0
	;debuglog "Allignment: Good"
	Case 1
	;debuglog "Allignment: Neutral"
	Case 2
		;debuglog "Allignment: Evil"
	End Select
	hire\race=ReadByte(fin)
	;debuglog "Race: "+race(hire\race)
	hire\class=ReadByte(fin)
	;debuglog "Class: "+class(hire\class)
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
		
	;debuglog "Might:"+hire\mgt
		;debuglog "Intellect:"+hire\inte
		;debuglog "Personality:"+hire\per
		;debuglog "Endurance:"+hire\endu
		;debuglog "Speed:"+hire\spd
		;debuglog "Accuracy:"+hire\acc
		;debuglog "Luck:"+hire\lck
		
		SeekFile(fin,((((a+24)-1)*130)+30))
		hire\thv=ReadByte(fin)
		;debuglog "Thievery: "+hire\thv
		hire\lvl=ReadByte(fin)
		hire\lvl=ReadByte(fin)
		;debuglog "Level: "+hire\lvl
		hire\age=ReadByte(fin)
		;debuglog "Age: "+hire\age
		hire\day=ReadByte(fin)
		;debuglog "Days old: "+hire\day
		hire\sPLVL=ReadByte(fin)
		;debuglog "Spell Level:"+hire\splvl
		hire\food=ReadByte(fin)
		hire\food=ReadByte(fin)
		;debuglog "Food:"+hire\food
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
				;debuglog "Equiped:"+item\name$+"+"+hire\equiplus[eq]
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
				;debuglog "Backpack:"+item\name$+"+"+hire\backplus[eq]
			End If
		Next
	Next
		SeekFile(fin,(((a+24)-1)*130)+80)
		temp$=Hex(ReadByte(fin))
		
		hire\skill[0]=hex2int(Mid(Right(temp$,2),1,1))
		hire\skill[1]=hex2int(Right(temp$,1))
		
				;debuglog "OFFSET:"+FilePos(fin)
		;debuglog "Skill 1: "+skill(hire\skill[0])
		;debuglog "Skill 2: "+skill(hire\skill[1])
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
		;debuglog "Has Spells"
		as$=""
		For spl=1 To 7

		as$=as$+"1-"+Str$(spl)+"(
		as$=as$+hire\spell[spl2]+") "
		spl2=spl2+1
		Next
		;debuglog as$
	as$=""

		For spl=1 To 7
		as$=as$+"2-"+Str$(spl)+"(
		as$=as$+hire\spell[spl2]+") "
		spl2=spl2+1
	
		Next
			;debuglog as$	
	as$=""

		For spl=1 To 6
			as$=as$+"3-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
				
		Next
				;debuglog as$
	as$=""

		For spl=1 To 6
			as$=as$+"4-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;debuglog as$
	as$=""

		For spl=1 To 5
			as$=as$+"5-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;debuglog as$
	as$=""

		For spl=1 To 5
			as$=as$+"6-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
			
		Next
				;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"7-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
		Next
				;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"8-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
		Next
				;debuglog as$
	as$=""

		For spl=1 To 4
			as$=as$+"9-"+Str$(spl)+"(
			as$=as$+hire\spell[spl2]+") "
			spl2=spl2+1
		Next
				;debuglog as$
		SeekFile(fin,(((a+24)-1)*130)+88)
		hire\SP=ReadShort(fin)
		hire\MSP=ReadShort(fin)
		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		hire\GEMS=temp1+(temp2*256)
		hire\HP=ReadShort(fin)
		hire\MHP=ReadShort(fin)
		;debuglog "HIT POINTS "+hire\Hp+"/"+hire\MHp
		;debuglog "SPELL POINTS "+hire\sp+"/"+hire\Msp

		temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
		temp4=ReadByte(fin)
			hire\exper=temp1+(temp2*256)+(temp3*65536)+(temp4*16777216)
temp1=ReadByte(fin)
		temp2=ReadByte(fin)
		temp3=ReadByte(fin)
			hire\cost=temp1+(temp2*256)+(temp3*65536)

;		;debuglog Right(Hex(temp1),2)+" "+Right(Hex(temp2),2)+" "+Right(Hex(temp3),2)+" "+Right(Hex(temp4),2)+" "
		;debuglog "Experience:"+hire\exper
	;debuglog "cost:"+hire\cost	
		;debuglog "Gems:"+hire\gems
		SeekFile(fin,(((a+24)-1)*130)+121)
		hire\plus=ReadByte(fin)
		;debuglog "Has Plus:"+Right(Bin(hire\plus),8)
		SeekFile(fin,((a+24)*130))
;	For b=0 To 107
;	temp=ReadByte(fin)
;	;debuglog 	Chr(temp)
;	Next
	;debuglog "End Of Hireling:"+a
	;debuglog ""

Next

fillchar(1)
EndIf
End Function



; roster editor code end.


; map editor code Starts
Function map_editor()
				bob=0
				cmap=0
				gui_showwin(mapedit)
				gui_winfront(mapedit)
				gui_activewin=mapedit
				gui_hidewin(main)
				
				update_map()
				Repeat
					Flip:SetBuffer BackBuffer():Cls
					For map.map = Each map
					If map\area=cmap
						If map\envion=3 And collis=0
							update_map()
							control()
						Else
							draw_grid(cmap,collis)
							in_control(cmap,collis)
						EndIf
					End If
					Next
					For map.map=Each map
					If map\area=cmap			
					gui_settext(msg_mmcm,map\name$)
					End If
					Next
					GUI()			
					Select EVENT$
					Case "GUI_GADHIT"		;GADGET CLICKED
						Select True
							Case gui_gadhit=cmd_mpquit
							Exit
							Case gui_gadhit=cmd_mpsavemap
							save_map()
							Case gui_gadhit=cmd_mploadmap
							save_map()							
							Case gui_gadhit=cmd_mpedmp
								collis=0
							Case gui_gadhit=cmd_mpedev
								collis=1
							Case gui_gadhit=cmd_mpedcl							
								collis=2
							Case gui_gadhit=cmd_mpnext
							unget_map(cmap)
							cmap=cmap+1
							If cmap>59 Then cmap=59
							get_map(cmap)
							Case gui_gadhit=cmd_mpprev
							unget_map(cmap)
							cmap=cmap-1
							If cmap<0 Then cmap=0
							get_map(cmap)
							
						End Select
					End Select
					Forever
 					gui_hidewin(mapedit)
					gui_showwin(MAIN)
					gui_activewin=MAIN
End Function
Function get_map(ID=0)
For map.map=Each map
If map\Area=ID

a=0
For y=15 To 0 Step -1
For x=0 To 15
	If map\envion=3
		If Mid$(map\mapdata[a],1,4)="0000"
			oy=15
		ElseIf Mid$(map\mapdata[a],1,4)="0001"
			oy=14
		ElseIf Mid$(map\mapdata[a],1,4)="0010"
			oy=13
		ElseIf Mid$(map\mapdata[a],1,4)="0011"
			oy=12
		ElseIf Mid$(map\mapdata[a],1,4)="0100"
			oy=11
		ElseIf Mid$(map\mapdata[a],1,4)="0101"
			oy=10
		ElseIf Mid$(map\mapdata[a],1,4)="0110"
			oy=9
		ElseIf Mid$(map\mapdata[a],1,4)="0111"
			oy=8
		ElseIf Mid$(map\mapdata[a],1,4)="1000"
			oy=7
		ElseIf Mid$(map\mapdata[a],1,4)="1001"
			oy=6
		ElseIf Mid$(map\mapdata[a],1,4)="1010"
			oy=5
		ElseIf Mid$(map\mapdata[a],1,4)="1011"
			oy=4
		ElseIf Mid$(map\mapdata[a],1,4)="1100"
			oy=3
		ElseIf Mid$(map\mapdata[a],1,4)="1101"
			oy=2
		ElseIf Mid$(map\mapdata[a],1,4)="1110"
			oy=1
		ElseIf Mid$(map\mapdata[a],1,4)="1111"
			oy=0
		EndIf

		
		If Mid$(map\mapdata[a],5,4)="0000"
			ox=0
		ElseIf Mid$(map\mapdata[a],5,4)="0001"
			ox=1
		ElseIf Mid$(map\mapdata[a],5,4)="0010"
			ox=2
		ElseIf Mid$(map\mapdata[a],5,4)="0011"
			ox=3
		ElseIf Mid$(map\mapdata[a],5,4)="0100"
			ox=4
		ElseIf Mid$(map\mapdata[a],5,4)="0101"
			ox=5
		ElseIf Mid$(map\mapdata[a],5,4)="0110"
			ox=6
		ElseIf Mid$(map\mapdata[a],5,4)="0111"
			ox=7
		ElseIf Mid$(map\mapdata[a],5,4)="1000"
			ox=8
		ElseIf Mid$(map\mapdata[a],5,4)="1001"
			ox=9
		ElseIf Mid$(map\mapdata[a],5,4)="1010"
			ox=10
		ElseIf Mid$(map\mapdata[a],5,4)="1011"
			ox=11
		ElseIf Mid$(map\mapdata[a],5,4)="1100"
			ox=12
		ElseIf Mid$(map\mapdata[a],5,4)="1101"
			ox=13
		ElseIf Mid$(map\mapdata[a],5,4)="1110"
			ox=14
		ElseIf Mid$(map\mapdata[a],5,4)="1111"
			ox=15
		EndIf
;		DebugLog "a:"+a+"- OX:"+ox+" OY:"+oy

		amap(x,y)=((oy)*16)+(ox)
		
		a=a+1
		
;		draw_mgrid()
		EndIf
Next
Next
End If
Next
End Function
Function unget_map(ID)
For map.map=Each map
	If map\area=ID
		If map\envion=3	
		For y=15 To 0 Step -1
			For x=0 To 15
				tmp$=Right(Hex(amap(x,y)),2)
				oy=hex2int(Left(tmp$,1))
				ox=hex2int(Right(tmp$,1))
				
				;DebugLog x+","+y+":"+tmp$+"="+OX+" , "+OY
				oxb$=Right(Bin(ox),4)
				;DebugLog oxb
				oyb$=" "
				Select oy
					Case 0
						oyb="1111"
					Case 1
						oyb="1110"
					Case 2
						oyb="1101"
					Case 3
						oyb="1100"
					Case 4
						oyb="1011"
					Case 5
						oyb="1010"
					Case 6
						oyb="1001"
					Case 7
						oyb="1000"
					Case 8
						oyb="0111"
					Case 9
						oyb="0110"
					Case 10
						oyb="0101"
					Case 11
						oyb="0100"
					Case 12
						oyb="0011"
					Case 13
						oyb="0010"
					Case 14
						oyb="0001"
					Case 15
						oyb="0000"
					End Select
					;DebugLog OYB$
					;DebugLog map\mapdata[a]
					map\mapdata[a]=oyb$+""+oxb$
					;DebugLog map\mapdata[a]
					a=a+1
			Next
		Next
		EndIf
	EndIf
Next
				
End Function
Function draw_grid(ID,col)
a=0

For map.map=Each map
If map\Area=ID
For x=0 To 15
For y=0 To 15
	ya=482-(x*32)
		xa=2+(y*32)

If col=0
	
			If Mid$(map\mapdata[a],1,2)="00"
			Color(50,50,50)
			Line xa,ya,xa+31,ya
			ElseIf Mid$(map\mapdata[a],1,2)="01"
			Color(128,128,128)
			Line xa,ya,xa+31,ya
			ElseIf Mid$(map\mapdata[a],1,2)="10"
			Color(255,255,255)
			Line xa,ya,xa+31,ya
			Rect xa+8,ya-8,16,8,0
			ElseIf Mid$(map\mapdata[a],1,2)="11"
			Color(255,125,0)
			Line xa,ya,xa+31,ya
			EndIf
			
			If Mid$(map\mapdata[a],3,2)="00"
			Color(50,50,50)
			Line xa+31,ya+31,xa+31,ya
			ElseIf Mid$(map\mapdata[a],3,2)="01"
			Color(128,128,128)
			Line xa+31,ya+31,xa+31,ya
			ElseIf Mid$(map\mapdata[a],3,2)="10"
			Color(255,255,255)
			Line xa+31,ya+31,xa+31,ya
			Rect (xa-8)+31,(ya-23)+31,8,16,0
			ElseIf Mid$(map\mapdata[a],3,2)="11"
			Color(255,125,0)
			Line xa+31,ya+31,xa+31,ya
			EndIf
			
			If Mid$(map\mapdata[a],5,2)="00"
			Color(50,50,50)


			Line xa,ya+31,xa+31,ya+31
			ElseIf Mid$(map\mapdata[a],5,2)="01"
						Color(128,128,128)
			Line xa,ya+31,xa+31,ya+31
						
				ElseIf Mid$(map\mapdata[a],5,2)="10"
			Color(255,255,255)
			Line xa,ya+31,xa+31,ya+31
			Rect (xa-23)+31,(ya)+31,16,8,0
			ElseIf Mid$(map\mapdata[a],5,2)="11"
			Color(255,125,0)
			Line xa,ya+31,xa+31,ya+31
			EndIf

		
			If Mid$(map\mapdata[a],7,2)="00"
			Color(50,50,50)
					    Line xa,ya,xa,ya+31

			ElseIf Mid$(map\mapdata[a],7,2)="01"
				Color(128,128,128)
					    Line xa,ya,xa,ya+31

			ElseIf Mid$(map\mapdata[a],7,2)="10"
			Color(255,255,255)
		    Line xa,ya,xa,ya+31
			Rect (xa),(ya+8),8,16,0


			ElseIf Mid$(map\mapdata[a],7,2)="11"
			Color(255,125,0)
					    Line xa,ya,xa,ya+31

			EndIf
	
		Else If col=2

			If Mid$(map\collmap[a],2,1)="0"
			Color(55,55,55)
			ElseIf Mid$(map\collmap[a],2,1)="1"
				Color(125,125,125)
			EndIf
			Line xa,ya,xa+31,ya
			If Mid$(map\collmap[a],4,1)="0"
			Color(55,55,55)
			ElseIf Mid$(map\collmap[a],4,1)="1"
				Color(125,125,125)
			EndIf
			Line xa+31,ya+31,xa+31,ya
			If Mid$(map\collmap[a],6,1)="0"
			Color(55,55,55)
			ElseIf Mid$(map\collmap[a],6,1)="1"
				Color(125,125,125)
			EndIf
			Line xa,ya+31,xa+31,ya+31
		
			If Mid$(map\collmap[a],8,1)="0"
			Color(55,55,55)
			ElseIf Mid$(map\collmap[a],8,1)="1"
				Color(125,125,125)
			EndIf
			
		    Line xa,ya,xa,ya+31

				Else If col=1
				Color(5,5,5)


		If Mid$(map\collmap[a],1,1)="1"
			Color(125,125,125)
		End If
		Rect xa,ya,32,32,1
		EndIf
		a=a+1
Next
Next
End If
Next
End Function 
Function in_control(ID,col)
map.map=First map
If map\area<>ID
map =After map
End If
If KeyHit(12) Then ct=ct-1
If KeyHit(13) Then ct=ct+1
If ct<0 Then ct=3
If ct>3 Then ct=0
	Select ct
		Case 0
			tl$="00"
		Case 1
			tl$="01"
		Case 2
			tl$="10"
		Case 3
			tl$="11"
		End Select
x=MouseX()
y=MouseY()
x2=x/32
y2=y/32
gx=(x2*32)+2
gy=(y2*32)+2
Color 255,255,255
xa=gx
ya=gy

	Color 255,0,0
If x2<16 And x2>=0 And y2<16 And y2>=0
Rect gx,gy,32,32,0
If x<gx+8 And y>gy+8 And y<gy+24

Select ct
		Case 0
			Color(50,50,50)
		    Line gx,gy,gx,gy+31
		Case 1
		Color(128,128,128)
		    Line gx,gy,gx,gy+31
		Case 2
		Color(255,255,255)
			Line gx,gy,gx,gy+31
			Rect (gx),(gy+8),8,16,0
		Case 3
			Color(255,125,0)
		    Line gx,gy,gx,gy+31
		End Select
		side=4

End If

If x>gx+24 And y>gy+8 And y<gy+24
	Select ct
		Case 0
			Color(50,50,50)
			Line xa+31,ya+31,xa+31,ya
		Case 1
			Color(128,128,128)
			Line xa+31,ya+31,xa+31,ya
		Case 2
			Color(255,255,255)
			Line xa+31,ya+31,xa+31,ya
			Rect (xa-8)+31,(ya-23)+31,8,16,0
		Case 3
			Color(255,125,0)
			Line xa+31,ya+31,xa+31,ya
	End Select
	side=2
End If
If y<gy+8 And x>gx+8 And x<gx+24
	Select ct
		Case 0
			Color(50,50,50)
			Line xa,ya,xa+31,ya
		Case 1			
			Color(128,128,128)
			Line xa,ya,xa+31,ya
		Case 2
			Color(255,255,255)
			Line xa,ya,xa+31,ya
			Rect (xa)+8,(ya),16,8,0

		Case 3
			Color(255,125,0)
			Line xa,ya,xa+31,ya
		End Select
	side=1
End If
If y>gy+24 And x>gx+8 And x<gx+24
		Select ct
		Case 0
			Color(50,50,50)
			Line xa,ya+31,xa+31,ya+31
		Case 1
			Color(128,128,128)
			Line xa,ya+31,xa+31,ya+31
		Case 2			
			Color(255,255,255)
			Line xa,ya+31,xa+31,ya+31
			Rect (xa-23)+31,(ya)+24,16,8,0
		Case 3
			Color(255,125,0)
			Line xa,ya+31,xa+31,ya+31
		End Select			
	side=3
End If

c=((15-y2)*16)+(x2)
EndIf
If col=0
	If MouseDown(1)
		dat1$=Mid$(map\mapdata[c],1,2)
		dat2$=Mid$(map\mapdata[c],3,2)
		dat3$=Mid$(map\mapdata[c],5,2)
		dat4$=Mid$(map\mapdata[c],7,2)
		If side=1
			dat1$=tl$
		Else If side=2
			dat2$=tl$
		Else If side=3
			dat3$=tl$
		Else If side=4
			dat4$=tl$
		End If
		map\mapdata[c]=dat1$+""+dat2$+""+dat3$+""+dat4$
	EndIf
End If
If col=1
	dat1$=Mid$(map\collmap[c],1,1)
	dat2$=Mid$(map\collmap[c],2,7)
	If MouseHit(1)
	If dat1$="1"
		 dat1$="0"
	Else
		 dat1$="1"
	End If
	End If
	map\collmap[c]=dat1+""+dat2
End If
If col=2
	If MouseHit(1)
		dat1$=Int(Mid$(map\collmap[c],1,1))
		dat2$=Int(Mid$(map\collmap[c],2,1))
		dat3$=Int(Mid$(map\collmap[c],3,1))
		dat4$=Int(Mid$(map\collmap[c],4,1))
		dat5$=Int(Mid$(map\collmap[c],5,1))
		dat6$=Int(Mid$(map\collmap[c],6,1))
		dat7$=Int(Mid$(map\collmap[c],7,1))
		dat8$=Int(Mid$(map\collmap[c],8,1))
		If side=1
		If dat2$="1"
		 dat2$="0"
	Else
		 dat2$="1"
	End If

		Else If side=2
			If dat4$="1"
			 dat4$="0"
			Else
			 dat4$="1"
			End If		
		Else If side=3
			If dat6$="1"
				 dat6$="0"
			Else
				 dat6$="1"
			End If
		Else If side=4
				If dat8$="1"
		 dat8$="0"
	Else
		 dat8$="1"
	End If
		End If
		map\collmap[c]=dat1$+""+dat2$+""+dat3$+""+dat4$+""+dat5$+""+dat6$+""+dat7$+""+dat8$
	EndIf
End If

Color 255,255,255

Text 10,500,x2+" "+y2+" "+c+" Area="+map\area+" Square Data="+map\collmap[c]+"   Tile"+tl$

End Function

Function update_map()
	For y=0 To 15
		For x=0 To 15

;				If map(x,y)<=10 And map(x,y)>0

					gx=x*32
					gy=y*32
					If amap(x,y)=255 Then amap(x,y)=15
					DrawImage graphicsfile,gx,gy,amap(x,y)
					
;				End If		
		Next ;x
	Next ;y

End Function


Function control()
x=MouseX()
y=MouseY()
x2=x/32
y2=y/32
cursorX=x2
cursory=y2
If cursory<0 Then cursory=0
If cursory>15 Then cursory=15
If cursorx<0 Then cursorx=0
If cursorx>15 Then cursorx=15

If MouseHit(1) Then amap(cursorx,cursory)=currenttile
If MouseHit(2) Then currenttile=amap(cursorx,cursory)
If KeyHit(12) Then currenttile=currenttile-1
If KeyHit(13) Then currenttile=currenttile+1
Color 255,255,255
Text 0,0,currenttile+" "+cursorX+" "+cursory
If currenttile<0 Then currenttile=255
If currenttile>255 Then currenttile=0

update_display()

End Function

Function update_display()

DrawImage graphicsfile,cursorx*32,cursory*32,currenttile 

;flash()
;Rect cursorx*28,cursory*22,29,23,0 
	
End Function


Function load_map(mapdat$="map.dat")
mapd=OpenFile(mapdat$)
attrib=OpenFile("attrib.dat")

For area=0 To 59
	map.map=New map
	map\area=area
	Select area
	Case 0
		map\name$="Middlegate"
	Case 1
		map\name$="Atlantium"
	Case 2
		map\name$="Tundra"
	Case 3
		map\name$="Volcania"
	Case 4
		map\name$="Sandsobar"
	Case 17
		map\name$="Middlegate Cavern"
	Case 18
		map\name$="Atlantium Cavern"
	Case 19
		map\name$="Tundra Cavern"
	Case 20
		map\name$="Volcania Cavern"
	Case 21
		map\name$="Sandsobar Cavern"
	Case 22
		map\name$="Coraks Cave"
	Case 23
		map\name$="Square Isle"
	Case 24
		map\name$="Tundra Cave"
	Case 25
		map\name$="Sarkins Mine"
	Case 26
		map\name$="Murry's Resort"
	Case 27
		map\name$="Druids Cavern"
	Case 28
		map\name$="Forbiddin Forest Cavern"
	Case 29
		map\name$="Dragons Dominion"
	Case 30
		map\name$="Dawns Mist Bog"
	Case 31
		map\name$="Gemmakers"	
	Case 32
		map\name$="Normadic Rift"	
	Case 41
		map\name$="Elemental Plain of Air"	
	Case 42
		map\name$="Elemental Plain of Fire"	
	Case 43
		map\name$="Elemental Plain of Earth"	
	Case 44
		map\name$="Elemental Plain of Water"	
	Case 45
		map\name$="dungeon Level 1"	
	Case 46
		map\name$="dungeon Level 2"	
	Case 47
		map\name$="dungeon Level 1"	
	Case 48
		map\name$="dungeon Level 2"	
	Case 49
		map\name$="dungeon Level 1"	
	Case 50
		map\name$="dungeon Level 2"	
	Case 51
		map\name$="Woodhaven Dungeon level 1"	
	Case 52
		map\name$="Woodhaven Dungeon Level 2"	
	Case 53
		map\name$="Ancients (Good)"	
	Case 54
		map\name$="Ancients (Evil)"	
	Case 55
		map\name$="Woodhaven"
		Case 56
		map\name$="Hillstone"	
	Case 57
		map\name$="Pinehurst"	
	Case 58
		map\name$="Luxus Palace Royale"	
	Case 59
		map\name$="the One in 800"	
	End Select

	aloc=(area*64)
	mloc=(area*512)
	SeekFile( mapd,mloc)
	SeekFile(attrib,aloc+4)
	tmp=ReadByte(attrib)
	If tmp>0
		map\envion=3
		SeekFile(attrib,aloc+21)
		map\name$="Outside Area: "+Right(Hex(ReadByte(attrib)),2)
	Else
		SeekFile(attrib,aloc+3)
		tmp=ReadByte(attrib)
		Select tmp
		Case 17
			map\envion=0
		Case 18
			map\envion=1
		Case 19
			map\envion=2
		Case 20
			map\envion=2
		End Select
		If map\area>40 And map\area<45
			map\envion=3
		EndIf
	EndIf
	SeekFile( mapd,mloc)
	For loc=0 To 255
		temp$=(ReadByte(mapd))
		ts$=Right$(Bin$(temp$),8)
		map\mapdata[loc]=ts
	Next
	SeekFile( mapd,mloc+256)
	For loc=0 To 255
		temp$=ReadByte(mapd)
		ts$=Right$(Bin$(temp$),8)
		map\collmap[loc]=ts$
	Next

	SeekFile(attrib,(area*64)+32)
	For a=0 To 255 Step 8
		roof=ReadByte(attrib)
		temparray(a+7)=Mid(Right(Bin(roof),8),1,1)
		temparray(a+6)=Mid(Right(Bin(roof),8),2,1)
		temparray(a+5)=Mid(Right(Bin(roof),8),3,1)
		temparray(a+4)=Mid(Right(Bin(roof),8),4,1)
		temparray(a+3)=Mid(Right(Bin(roof),8),5,1)
		temparray(a+2)=Mid(Right(Bin(roof),8),6,1)
		temparray(a+1)=Mid(Right(Bin(roof),8),7,1)
		temparray(a+0)=Mid(Right(Bin(roof),8),8,1)

	Next
	For a=0 To 255
		map\roofdata[a]=temparray(255-a)
	Next

Next


End Function

Function save_map(file$="map.dat")
fo=OpenFile(file$)
For map.map=Each map
	area=map\area
	mloc=(area*512)
	SeekFile( fo,mloc)
	For a=0 To 255
	;	DebugLog a+": "+map\mapdata[a]+" "+Int(map\mapdata[a])+" "+Hex(Int(map\mapdata[a]))
			If Mid$(map\mapdata[a],1,4)="0000"
			oy=0
		ElseIf Mid$(map\mapdata[a],1,4)="0001"
			oy=1
		ElseIf Mid$(map\mapdata[a],1,4)="0010"
			oy=2
		ElseIf Mid$(map\mapdata[a],1,4)="0011"
			oy=3
		ElseIf Mid$(map\mapdata[a],1,4)="0100"
			oy=4
		ElseIf Mid$(map\mapdata[a],1,4)="0101"
			oy=5
		ElseIf Mid$(map\mapdata[a],1,4)="0110"
			oy=6
		ElseIf Mid$(map\mapdata[a],1,4)="0111"
			oy=7
		ElseIf Mid$(map\mapdata[a],1,4)="1000"
			oy=8
		ElseIf Mid$(map\mapdata[a],1,4)="1001"
			oy=9
		ElseIf Mid$(map\mapdata[a],1,4)="1010"
			oy=10
		ElseIf Mid$(map\mapdata[a],1,4)="1011"
			oy=11
		ElseIf Mid$(map\mapdata[a],1,4)="1100"
			oy=12
		ElseIf Mid$(map\mapdata[a],1,4)="1101"
			oy=13
		ElseIf Mid$(map\mapdata[a],1,4)="1110"
			oy=14
		ElseIf Mid$(map\mapdata[a],1,4)="1111"
			oy=15
		EndIf

		
		If Mid$(map\mapdata[a],5,4)="0000"
			ox=0
		ElseIf Mid$(map\mapdata[a],5,4)="0001"
			ox=1
		ElseIf Mid$(map\mapdata[a],5,4)="0010"
			ox=2
		ElseIf Mid$(map\mapdata[a],5,4)="0011"
			ox=3
		ElseIf Mid$(map\mapdata[a],5,4)="0100"
			ox=4
		ElseIf Mid$(map\mapdata[a],5,4)="0101"
			ox=5
		ElseIf Mid$(map\mapdata[a],5,4)="0110"
			ox=6
		ElseIf Mid$(map\mapdata[a],5,4)="0111"
			ox=7
		ElseIf Mid$(map\mapdata[a],5,4)="1000"
			ox=8
		ElseIf Mid$(map\mapdata[a],5,4)="1001"
			ox=9
		ElseIf Mid$(map\mapdata[a],5,4)="1010"
			ox=10
		ElseIf Mid$(map\mapdata[a],5,4)="1011"
			ox=11
		ElseIf Mid$(map\mapdata[a],5,4)="1100"
			ox=12
		ElseIf Mid$(map\mapdata[a],5,4)="1101"
			ox=13
		ElseIf Mid$(map\mapdata[a],5,4)="1110"
			ox=14
		ElseIf Mid$(map\mapdata[a],5,4)="1111"
			ox=15
		EndIf

		temp=((oy)*16)+(ox)
		If area=0
		DebugLog "a:"+a+"- OX:"+ox+" OY:"+oy+" "+temp
EndIf
		WriteByte(fo,temp)
	Next
		For a=0 To 255
		If Mid$(map\collmap[a],1,4)="0000"
			oy=0
		ElseIf Mid$(map\collmap[a],1,4)="0001"
			oy=1
		ElseIf Mid$(map\collmap[a],1,4)="0010"
			oy=2
		ElseIf Mid$(map\collmap[a],1,4)="0011"
			oy=3
		ElseIf Mid$(map\collmap[a],1,4)="0100"
			oy=4
		ElseIf Mid$(map\collmap[a],1,4)="0101"
			oy=5
		ElseIf Mid$(map\collmap[a],1,4)="0110"
			oy=6
		ElseIf Mid$(map\collmap[a],1,4)="0111"
			oy=7
		ElseIf Mid$(map\collmap[a],1,4)="1000"
			oy=8
		ElseIf Mid$(map\collmap[a],1,4)="1001"
			oy=9
		ElseIf Mid$(map\collmap[a],1,4)="1010"
			oy=10
		ElseIf Mid$(map\collmap[a],1,4)="1011"
			oy=11
		ElseIf Mid$(map\collmap[a],1,4)="1100"
			oy=12
		ElseIf Mid$(map\collmap[a],1,4)="1101"
			oy=13
		ElseIf Mid$(map\collmap[a],1,4)="1110"
			oy=14
		ElseIf Mid$(map\collmap[a],1,4)="1111"
			oy=15
		EndIf

		
		If Mid$(map\collmap[a],5,4)="0000"
			ox=0
		ElseIf Mid$(map\collmap[a],5,4)="0001"
			ox=1
		ElseIf Mid$(map\collmap[a],5,4)="0010"
			ox=2
		ElseIf Mid$(map\collmap[a],5,4)="0011"
			ox=3
		ElseIf Mid$(map\collmap[a],5,4)="0100"
			ox=4
		ElseIf Mid$(map\collmap[a],5,4)="0101"
			ox=5
		ElseIf Mid$(map\collmap[a],5,4)="0110"
			ox=6
		ElseIf Mid$(map\collmap[a],5,4)="0111"
			ox=7
		ElseIf Mid$(map\collmap[a],5,4)="1000"
			ox=8
		ElseIf Mid$(map\collmap[a],5,4)="1001"
			ox=9
		ElseIf Mid$(map\collmap[a],5,4)="1010"
			ox=10
		ElseIf Mid$(map\collmap[a],5,4)="1011"
			ox=11
		ElseIf Mid$(map\collmap[a],5,4)="1100"
			ox=12
		ElseIf Mid$(map\collmap[a],5,4)="1101"
			ox=13
		ElseIf Mid$(map\collmap[a],5,4)="1110"
			ox=14
		ElseIf Mid$(map\collmap[a],5,4)="1111"
			ox=15
		EndIf

		temp=((oy)*16)+(ox)
		If area=0
		DebugLog "a:"+a+"- OX:"+ox+" OY:"+oy+" "+temp
EndIf
		WriteByte(fo,temp)
	Next

Next
End Function
; map editor code ends.