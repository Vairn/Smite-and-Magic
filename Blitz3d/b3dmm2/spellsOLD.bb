Function Create_clerical()
;Cleric
; level 1 spells
; Spell 1 Apparition
cleric.clerical=New clerical
cleric\ID=1
cleric\level=1
cleric\Spell=1
cleric\effect=10 
cleric\cost[1]=1
cleric\cost[2]=0
cleric\combat=1
cleric\name$="Apparition"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afriad. 
cleric\desc$[3]="Reducing their chance To hit."
;1-2
cleric.clerical=New clerical
cleric\ID=2
cleric\level=1
cleric\Spell=2
cleric\effect=0
cleric\cost[1]=1
cleric\cost[2]=0
cleric\combat=2
cleric\name$="Awaken"
cleric\desc$[0]="Awakens all Sleeping Party"
cleric\desc$[1]="Memebers, instantaneously"
cleric\desc$[2]="Cancelling the Sleep Condidtion"
cleric\desc$[3]="May be critical if the party"
cleric\desc$[4]="is attacked during rest."
;1-3
cleric.clerical=New clerical
cleric\ID=3
cleric\level=1
cleric\Spell=3
cleric\effect=0 
cleric\cost[1]=1
cleric\cost[2]=0
cleric\combat=1
cleric\name$="Bless"
cleric\desc$[0]="Increases the accuracy with which "
cleric\desc$[1]="all characters fight, For the"
cleric\desc$[2]="duration of combat. 


cleric.clerical=New clerical
cleric\level=1
cleric\ID=4
cleric\Spell=4
cleric\effect=1 
cleric\cost[1]=1
cleric\cost[2]=0
cleric\damage[1]=8
cleric\damage[2]=8
cleric\combat=2
cleric\name$="First Aid"
cleric\desc$[0]="Heals minor battle wounds,
cleric\desc$[1]="restoring 8 Hit Points To that"
cleric\desc$[2]="character."


cleric.clerical=New clerical
cleric\ID=5
cleric\level=1
cleric\Spell=5
cleric\effect=0
cleric\combat=0 
cleric\cost[1]=1
cleric\cost[2]=0
cleric\name$="Light"
cleric\desc$[0]="Gives the party 1 light factor,
cleric\desc$[1]="which is sufficient to light up"
cleric\desc$[2]="1 dark area. Multiple light spells" 
cleric\desc$[3]="can be cast To accumulate"
cleric\desc$[4]="multiple light factors"

cleric.clerical=New clerical
cleric\ID=6
cleric\level=1
cleric\Spell=6
cleric\effect=1
cleric\cost[1]=1
cleric\cost[2]=1
cleric\combat=2
cleric\damage[1]=1
cleric\damage[1]=10
cleric\name$="Power Cure"
cleric\desc$[0]="Restores character's health"
cleric\desc$[1]="and 1-10 Hit Points per"
cleric\desc$[2]="experience level of caster"



cleric.clerical=New clerical
cleric\ID=7
cleric\level=1
cleric\Spell=7
cleric\effect=0 ; must be undead 
cleric\cost[1]=1
cleric\cost[2]=0
cleric\combat=1
cleric\name$="Turn Undead"
cleric\desc$[0]="Destroys some or all undead monsters,
 
cleric\desc$[1]="depending on caster's experience"
cleric\desc$[2]="evel and monster's power level."

;SPELL LEVEL 2
cleric.clerical=New clerical
cleric\ID=8
cleric\level=2
cleric\Spell=1
cleric\effect=1
cleric\cost[1]=2
cleric\cost[2]=0
cleric\damage[1]=15
cleric\damage[2]=15
cleric\combat=2
cleric\name$="Cure Wounds
cleric\desc$[0]="Cures more serious wounds, restoring"
cleric\desc$[1]="15 Hit Points to the character."

cleric.clerical=New clerical
cleric\ID=9
cleric\level=2
cleric\Spell=2
cleric\effect=1 ; must be undead 
cleric\cost[1]=2
cleric\cost[2]=1
cleric\damage[1]=6
cleric\damage[2]=6
cleric\name$="Heroism"
cleric\desc$[0]="Temporarily elevates a character 6 levels"
cleric\desc$[1]="of experience."
cleric\desc$[2]="Spell lasts for the duration of combat."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=10
cleric\level=2
cleric\Spell=3
cleric\effect=0 ; must be undead 
cleric\cost[1]=2
cleric\cost[2]=0
cleric\name$="Natures Gate"
cleric\desc$[0]="Using the forces of nature, opens"
cleric\desc$[1]="a portal between two locations"
cleric\desc$[2]="in the land of Cron. These locations" 
cleric\desc$[3]="vary with time (days/years)."
cleric\combat=0



cleric.clerical=New clerical
cleric\ID=11
cleric\level=2
cleric\Spell=4
cleric\effect=0 ; must be undead 
cleric\cost[1]=2
cleric\cost[2]=0
cleric\damage[1]=2
cleric\damage[2]=16
cleric\name$="Pain"
cleric\desc$[0]="Cripples monster with pain,"
cleric\desc$[1]="inflicting 2-16 damage points"
cleric\desc$[2]="unless the monster is immune to pain." 
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=12
cleric\level=2
cleric\Spell=5
cleric\effect=0 ; must be undead 
cleric\cost[1]=2
cleric\cost[2]=1
cleric\name$="Protection from Elements"
cleric\desc$[0]=" Increases all character's resistance to "
cleric\desc$[1]="fear, cold, fire, poison, acid and 
cleric\desc$[2]="and electricity. Amount of the increase 
cleric\desc$[3]="depends on the caster's experience level."
cleric\desc$[4]="Spell lasts 1 day."
cleric\combat=2

cleric.clerical=New clerical
cleric\ID=13
cleric\level=2
cleric\Spell=6
cleric\effect=4 ; plus 1 per level
cleric\cost[1]=2
cleric\cost[2]=0
cleric\name$="Silence"
cleric\desc$[0]="Attacks with a ray of intensive cold
cleric\desc$[1]="for the duration of combat,"
cleric\desc$[2]="or until they overcome the spell.
cleric\combat=2

cleric.clerical=New clerical
cleric\ID=14
cleric\level=2
cleric\Spell=7
cleric\effect=10
cleric\cost[1]=2
cleric\cost[2]=1
cleric\name$="Weaken"
cleric\desc$[0]="Weakens all monsters affected,"
cleric\desc$[1]="reducing their physical damage"
cleric\desc$[2]=" by half until the spell is overcome."
cleric\combat=2

;spell level 3
cleric.clerical=New clerical
cleric\ID=15
cleric\level=3
cleric\Spell=1
cleric\effect=5 ; must be undead 
cleric\cost[1]=3
cleric\cost[2]=2
cleric\damage[1]=25
cleric\damage[2]=25
cleric\name$="Cold Ray"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1


cleric.clerical=New clerical
cleric\ID=16
cleric\level=3
cleric\Spell=2
cleric\effect=0 ; must be undead 
cleric\cost[1]=3
cleric\cost[2]=2
cleric\damage[1]=8
cleric\damage[2]=8
cleric\name$="Create Food"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=2

cleric.clerical=New clerical
cleric\ID=17
cleric\level=3
cleric\Spell=3
cleric\effect=0 ; must be undead 
cleric\cost[1]=3
cleric\cost[2]=0
cleric\name$="Cure Poison"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=2

cleric.clerical=New clerical
cleric\ID=18
cleric\level=3
cleric\Spell=4
cleric\effect=5 ; must be undead 
cleric\cost[1]=3
cleric\cost[2]=0
cleric\name$="Immobilize"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=19
cleric\level=3
cleric\Spell=5
cleric\effect=0 ; must be undead 
cleric\cost[1]=3
cleric\cost[2]=0
cleric\name$="Lasting Light"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=2

cleric.clerical=New clerical
cleric\ID=20
cleric\level=3
cleric\Spell=6
cleric\effect=0 ; must be undead 
cleric\cost[1]=3
cleric\cost[2]=2
cleric\name$="Walk on Water"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=0

; level 4
cleric.clerical=New clerical
cleric\ID=21
cleric\level=4
cleric\Spell=1
cleric\effect=0 ; must be undead 
cleric\cost[1]=4
cleric\cost[2]=3
cleric\name$="Acid Spray"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=22
cleric\level=4
cleric\Spell=2
cleric\effect=0 ; must be undead 
cleric\cost[1]=4
cleric\cost[2]=3
cleric\name$="Air Transmutation"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=0

cleric.clerical=New clerical
cleric\ID=23
cleric\level=4
cleric\Spell=3
cleric\effect=0 ; must be undead 
cleric\cost[1]=4
cleric\cost[2]=0
cleric\name$="Cure Disease"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=2

cleric.clerical=New clerical
cleric\ID=24
cleric\level=4
cleric\Spell=4
cleric\effect=0 ; must be undead 
cleric\cost[1]=4
cleric\cost[2]=3
cleric\name$="Restore Alignment"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=0

cleric.clerical=New clerical
cleric\ID=25
cleric\level=4
cleric\Spell=5
cleric\effect=0 ; must be undead 
cleric\cost[1]=4
cleric\cost[2]=0
cleric\name$="Surface"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=0

cleric.clerical=New clerical
cleric\ID=26
cleric\level=4
cleric\Spell=6
cleric\effect=0 ; must be undead 
cleric\cost[1]=4
cleric\cost[2]=3
cleric\name$="Holy Bonus"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1
; level 5
cleric.clerical=New clerical
cleric\ID=27
cleric\level=5
cleric\Spell=1
cleric\effect=0 ; must be undead 
cleric\cost[1]=5
cleric\cost[2]=5
cleric\name$="Air Encasement"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=28
cleric\level=5
cleric\Spell=2
cleric\effect=0 ; must be undead 
cleric\cost[1]=5
cleric\cost[2]=5
cleric\name$="Deadly Swarm"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=29
cleric\level=5
cleric\Spell=3
cleric\effect=0 ; must be undead 
cleric\cost[1]=5
cleric\cost[2]=5
cleric\name$="Frenzy"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=30
cleric\level=5
cleric\Spell=4
cleric\effect=0 ; must be undead 
cleric\cost[1]=5
cleric\cost[2]=5
cleric\name$="Paralyse"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them To be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=31
cleric\level=5
cleric\Spell=5
cleric\effect=0 ; must be undead 
cleric\cost[1]=5
cleric\cost[2]=5
cleric\name$="Remove Condidtion"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=2

; level 6
cleric.clerical=New clerical
cleric\ID=32
cleric\level=6
cleric\Spell=1
cleric\effect=0 ; must be undead 
cleric\cost[1]=6
cleric\cost[2]=6
cleric\name$="Earth Transmutation"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=0

cleric.clerical=New clerical
cleric\ID=33
cleric\level=6
cleric\Spell=2
cleric\effect=0 ; must be undead 
cleric\cost[1]=6
cleric\cost[2]=6
cleric\name$="Rejuvernate
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=0

cleric.clerical=New clerical
cleric\ID=34
cleric\level=6
cleric\Spell=3
cleric\effect=0 ; must be undead 
cleric\cost[1]=6
cleric\cost[2]=6
cleric\name$="Stone to Flesh"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=2

cleric.clerical=New clerical
cleric\ID=35
cleric\level=6
cleric\Spell=4
cleric\effect=0 ; must be undead 
cleric\cost[1]=6
cleric\cost[2]=6
cleric\name$="Water Encasement"
cleric\desc$[0]="Creates a Frighatening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=36
cleric\level=6
cleric\Spell=5
cleric\effect=0 ; must be undead 
cleric\cost[1]=6
cleric\cost[2]=6
cleric\name$="Water Transmutation"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=0

; level 7
cleric.clerical=New clerical
cleric\ID=37
cleric\level=7
cleric\Spell=1
cleric\effect=0 ; must be undead 
cleric\cost[1]=7
cleric\cost[2]=7
cleric\name$="Earth Encasement"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=38
cleric\level=7
cleric\Spell=2
cleric\effect=0 ; must be undead 
cleric\cost[1]=7
cleric\cost[2]=7
cleric\name$="Fiely Flail"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=39
cleric\level=7
cleric\Spell=3
cleric\effect=0 ; must be undead 
cleric\cost[1]=7
cleric\cost[2]=7
cleric\name$="Moon Ray"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=40
cleric\level=7
cleric\Spell=4
cleric\effect=0 ; must be undead 
cleric\cost[1]=7
cleric\cost[2]=7
cleric\name$="Raise Dead"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=2

; level 8
cleric.clerical=New clerical
cleric\ID=41
cleric\level=8
cleric\Spell=1
cleric\effect=0 ; must be undead 
cleric\cost[1]=8
cleric\cost[2]=8
cleric\name$="Fire Encasement"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=42
cleric\level=8
cleric\Spell=2
cleric\effect=0 ; must be undead 
cleric\cost[1]=8
cleric\cost[2]=8
cleric\name$="Fire Transmutation"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=0

cleric.clerical=New clerical
cleric\ID=43
cleric\level=8
cleric\Spell=3
cleric\effect=0 ; must be undead 
cleric\cost[1]=8
cleric\cost[2]=8
cleric\name$="Mass Distortion"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=44
cleric\level=8
cleric\Spell=4
cleric\effect=0 ; must be undead 
cleric\cost[1]=8
cleric\cost[2]=8
cleric\name$="Town Portal"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=0

; level 9
cleric.clerical=New clerical
cleric\ID=45
cleric\level=9
cleric\Spell=1
cleric\effect=0 ; must be undead 
cleric\cost[1]=10
cleric\cost[2]=20
cleric\name$="Divine Intervention"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=46
cleric\level=9
cleric\Spell=2
cleric\effect=0 ; must be undead 
cleric\cost[1]=10
cleric\cost[2]=10
cleric\name$="Holy Word"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=1

cleric.clerical=New clerical
cleric\ID=47
cleric\level=9
cleric\Spell=3
cleric\effect=0 ; must be undead 
cleric\cost[1]=10
cleric\cost[2]=10
cleric\name$="Resurection"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=0

cleric.clerical=New clerical
cleric\ID=48
cleric\level=9
cleric\Spell=4
cleric\effect=0 ; must be undead 
cleric\cost[1]=10
cleric\cost[2]=50
cleric\name$="Uncurse Item"
cleric\desc$[0]="Creates a Frightening Apparition"
cleric\desc$[1]="in the monsters memory causing"
cleric\desc$[2]="them to be afried. 
cleric\desc$[3]="Reducing their chance To hit."
cleric\combat=0




End Function

Function create_sorceror()
;sorc
; level 1 spells
; Spell 1 Apparition
sorc.sorceror=New sorceror
sorc\ID=1
sorc\level=1
sorc\Spell=1
sorc\effect=10 
sorc\cost[1]=1
sorc\cost[2]=0
sorc\combat=2
sorc\name$="Awaken"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
;1-2
sorc.sorceror=New sorceror
sorc\ID=2
sorc\level=1
sorc\Spell=2
sorc\effect=0
sorc\cost[1]=1
sorc\cost[2]=0
sorc\combat=0
sorc\name$="Detect Magic"
sorc\desc$[0]="Awakens all Sleeping Party"
sorc\desc$[1]="Memebers, instantaneously"
sorc\desc$[2]="Cancelling the Sleep Condidtion"
sorc\desc$[3]="May be critical if the party"
sorc\desc$[4]="is attacked during rest."
;1-3
sorc.sorceror=New sorceror
sorc\ID=3
sorc\level=1
sorc\Spell=3
sorc\effect=1
sorc\cost[1]=1
sorc\cost[2]=1
sorc\damage[1]=1
sorc\damage[2]=6
sorc\combat=1
sorc\name$="Energy Blast"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."

sorc.sorceror=New sorceror
sorc\ID=4
sorc\level=1
sorc\Spell=4
sorc\effect=1 
sorc\cost[1]=1
sorc\cost[2]=0
sorc\damage[1]=2
sorc\damage[2]=8
sorc\combat=1
sorc\name$="Flame Arrow"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."

sorc.sorceror=New sorceror
sorc\ID=5
sorc\level=1
sorc\Spell=5
sorc\effect=0
sorc\combat=0 
sorc\cost[1]=1
sorc\cost[2]=0
sorc\name$="Light"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."

sorc.sorceror=New sorceror
sorc\ID=6
sorc\level=1
sorc\Spell=6
sorc\effect=1
sorc\cost[1]=1
sorc\cost[2]=0
sorc\combat=0
sorc\damage[1]=1
sorc\damage[1]=10
sorc\name$="Location"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."

sorc.sorceror=New sorceror
sorc\ID=7
sorc\level=1
sorc\Spell=7
sorc\effect=4
sorc\cost[1]=1
sorc\cost[2]=0
sorc\combat=1
sorc\name$="Sleep"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
;SPELL LEVEL 2
sorc.sorceror=New sorceror
sorc\ID=8
sorc\level=2
sorc\Spell=1
sorc\effect=1
sorc\cost[1]=2
sorc\cost[2]=0
sorc\damage[1]=5
sorc\damage[2]=5
sorc\combat=0
sorc\name$="Eagle Eye"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."

sorc.sorceror=New sorceror
sorc\ID=9
sorc\level=2
sorc\Spell=2
sorc\effect=1 ; must be undead 
sorc\cost[1]=2
sorc\cost[2]=0
sorc\damage[1]=4
sorc\damage[2]=16
sorc\name$="Electric Arrow"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=10
sorc\level=2
sorc\Spell=3
sorc\effect=1 ; must be undead 
sorc\cost[1]=2
sorc\cost[2]=1
sorc\name$="Identerfy Monster"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=11
sorc\level=2
sorc\Spell=4
sorc\effect=0 ; must be undead 
sorc\cost[1]=2
sorc\cost[2]=1
sorc\name$="Jump"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=0

sorc.sorceror=New sorceror
sorc\ID=12
sorc\level=2
sorc\Spell=5
sorc\effect=0 ; must be undead 
sorc\cost[1]=2
sorc\cost[2]=0
sorc\damage[1]=2
sorc\damage[2]=16
sorc\name$="Levitate"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=13
sorc\level=2
sorc\Spell=6
sorc\effect=4 ; plus 1 per level
sorc\cost[1]=2
sorc\cost[2]=0
sorc\name$="Lloyd's Beacon"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=2

sorc.sorceror=New sorceror
sorc\ID=14
sorc\level=2
sorc\Spell=7
sorc\effect=10
sorc\cost[1]=1
sorc\cost[2]=1
sorc\name$="Protection from Magic"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=2

;spell level 3
sorc.sorceror=New sorceror
sorc\ID=15
sorc\level=3
sorc\Spell=1
sorc\effect=1 ; must be undead 
sorc\cost[1]=1
sorc\cost[2]=2
sorc\damage[1]=2
sorc\damage[2]=8
sorc\name$="Acid Stream"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1


sorc.sorceror=New sorceror
sorc\ID=16
sorc\level=3
sorc\Spell=2
sorc\effect=0 ; must be undead 
sorc\cost[1]=3
sorc\cost[2]=0
sorc\damage[1]=8
sorc\damage[2]=8
sorc\name$="Fly"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=0

sorc.sorceror=New sorceror
sorc\ID=17
sorc\level=3
sorc\Spell=3
sorc\effect=0 ; must be undead 
sorc\cost[1]=3
sorc\cost[2]=0
sorc\name$="Invisiblility"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=18
sorc\level=3
sorc\Spell=4
sorc\effect=4 ; must be undead 
sorc\cost[1]=1
sorc\cost[2]=2
sorc\name$="Lightning Bolt"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=19
sorc\level=3
sorc\Spell=5
sorc\effect=4 ; must be undead 
sorc\cost[1]=3
sorc\cost[2]=2
sorc\name$="Web"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=3

sorc.sorceror=New sorceror
sorc\ID=20
sorc\level=3
sorc\Spell=6
sorc\effect=0 ; must be undead 
sorc\cost[1]=3
sorc\cost[2]=2
sorc\name$="Wizards Eye"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=0
; level 4
sorc.sorceror=New sorceror
sorc\ID=21
sorc\level=4
sorc\Spell=1
sorc\effect=0 ; must be undead 
sorc\cost[1]=1
sorc\cost[2]=3
sorc\name$="Cold beam"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1
sorc.sorceror=New sorceror
sorc\ID=22
sorc\level=4
sorc\Spell=2
sorc\effect=0 ; must be undead 
sorc\cost[1]=4
sorc\cost[2]=3
sorc\name$="Feeble Mind"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=23
sorc\level=4
sorc\Spell=3
sorc\effect=0 ; must be undead 
sorc\cost[1]=1
sorc\cost[2]=3
sorc\name$="Fireball"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=24
sorc\level=4
sorc\Spell=4
sorc\effect=0 ; must be undead 
sorc\cost[1]=4
sorc\cost[2]=0
sorc\name$="Guard Dog"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=0

sorc.sorceror=New sorceror
sorc\ID=25
sorc\level=4
sorc\Spell=5
sorc\effect=0 ; must be undead 
sorc\cost[1]=4
sorc\cost[2]=0
sorc\name$="Shield"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=26
sorc\level=4
sorc\Spell=6
sorc\effect=0 ; must be undead 
sorc\cost[1]=4
sorc\cost[2]=3
sorc\name$="Time Distortion"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1
; level 5


sorc.sorceror=New sorceror
sorc\ID=27
sorc\level=5
sorc\Spell=1
sorc\effect=0 ; must be undead 
sorc\cost[1]=5
sorc\cost[2]=5
sorc\name$="Disrupt"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=28
sorc\level=5
sorc\Spell=2
sorc\effect=0 ; must be undead 
sorc\cost[1]=5
sorc\cost[2]=5
sorc\name$="Fingers of Death"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=29
sorc\level=5
sorc\Spell=3
sorc\effect=0 ; must be undead 
sorc\cost[1]=2
sorc\cost[2]=5
sorc\name$="Sand Storm"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=30
sorc\level=5
sorc\Spell=4
sorc\effect=0 ; must be undead 
sorc\cost[1]=5
sorc\cost[2]=0
sorc\name$="Shelter"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=0

sorc.sorceror=New sorceror
sorc\ID=31
sorc\level=5
sorc\Spell=5
sorc\effect=0 ; must be undead 
sorc\cost[1]=5
sorc\cost[2]=0
sorc\name$="Teleport"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=0
; level 6
sorc.sorceror=New sorceror
sorc\ID=32
sorc\level=6
sorc\Spell=1
sorc\effect=0 ; must be undead 
sorc\cost[1]=6
sorc\cost[2]=6
sorc\name$="Disintergration"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=33
sorc\level=6
sorc\Spell=2
sorc\effect=0 ; must be undead 
sorc\cost[1]=6
sorc\cost[2]=6
sorc\name$="Entrapment"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=34
sorc\level=6
sorc\Spell=3
sorc\effect=0 ; must be undead 
sorc\cost[1]=2
sorc\cost[2]=6
sorc\name$="Fantastic Freeze"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=35
sorc\level=6
sorc\Spell=4
sorc\effect=0 ; must be undead 
sorc\cost[1]=3
sorc\cost[2]=2
sorc\name$="Recharge Item"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=0

sorc.sorceror=New sorceror
sorc\ID=36
sorc\level=6
sorc\Spell=5
sorc\effect=0 ; must be undead 
sorc\cost[1]=2
sorc\cost[2]=6
sorc\name$="Supershock"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

; level 7
sorc.sorceror=New sorceror
sorc\ID=37
sorc\level=7
sorc\Spell=1
sorc\effect=0 ; must be undead 
sorc\cost[1]=5
sorc\cost[2]=7
sorc\name$="Dancing Sword"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=38
sorc\level=7
sorc\Spell=2
sorc\effect=0 ; must be undead 
sorc\cost[1]=7
sorc\cost[2]=100
sorc\name$="Duplication"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=0

sorc.sorceror=New sorceror
sorc\ID=39
sorc\level=7
sorc\Spell=3
sorc\effect=0 ; must be undead 
sorc\cost[1]=7
sorc\cost[2]=7
sorc\name$="Etherealise"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=0

sorc.sorceror=New sorceror
sorc\ID=40
sorc\level=7
sorc\Spell=4
sorc\effect=0 ; must be undead 
sorc\cost[1]=7
sorc\cost[2]=7
sorc\name$="Prismatic Light"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

; level 8
sorc.sorceror=New sorceror
sorc\ID=41
sorc\level=8
sorc\Spell=1
sorc\effect=0 ; must be undead 
sorc\cost[1]=3
sorc\cost[2]=8
sorc\name$="Incinerate"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=42
sorc\level=8
sorc\Spell=2
sorc\effect=0 ; must be undead 
sorc\cost[1]=3
sorc\cost[2]=8
sorc\name$="Mega Volts"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=43
sorc\level=8
sorc\Spell=3
sorc\effect=0 ; must be undead 
sorc\cost[1]=8 ; plus 1 per monster
sorc\cost[2]=8
sorc\name$="Meteor Shower"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=0
sorc.sorceror=New sorceror
sorc\ID=44
sorc\level=8
sorc\Spell=4
sorc\effect=0 ; must be undead 
sorc\cost[1]=8
sorc\cost[2]=8
sorc\name$="Power Shield"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

; level 9
sorc.sorceror=New sorceror
sorc\ID=45
sorc\level=9
sorc\Spell=1
sorc\effect=0 ; must be undead 
sorc\cost[1]=10
sorc\cost[2]=10
sorc\name$="Implosion"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=46
sorc\level=9
sorc\Spell=2
sorc\effect=0 ; must be undead 
sorc\cost[1]=3
sorc\cost[2]=10
sorc\name$="Inferno"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=47
sorc\level=9
sorc\Spell=3
sorc\effect=0 ; must be undead 
sorc\cost[1]=10 ; +1 per monster
sorc\cost[2]=20
sorc\name$="Star Burst"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=1

sorc.sorceror=New sorceror
sorc\ID=48
sorc\level=9
sorc\Spell=4
sorc\effect=0 ; must be undead 
sorc\cost[1]=50 ; per plus
sorc\cost[2]=50
sorc\name$="Enchant Item"
sorc\desc$[0]="Creates a Frightening Apparition"
sorc\desc$[1]="in the monsters memory causing"
sorc\desc$[2]="them To be afried. 
sorc\desc$[3]="Reducing their chance To hit."
sorc\combat=0

End Function


; spells and monster abilities.
; Clerical Spells
; Level 1
Function CS_1_1(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=1 And cleric\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+cleric\effect
				effected=Rnd(bmon\mres)
				If effected<10
					DebugLog "monster:"+bmon\ID+" is affriad"
				EndIf
			EndIf
		Next
	EndIf
Next
End Function

Function CS_1_2(caster)
For cleric.clerical=Each clerical
	If cleric\Level=1 And cleric\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			char\asleep=0
		Next
	EndIf
Next
End Function

Function CS_1_3(caster)
For cleric.clerical=Each clerical
	If cleric\Level=1 And cleric\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			char\tacc=char\acc+10
		Next
		EndIf
Next
End Function

Function CS_1_4(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=1 And cleric\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			If char\ID=target
				char\HP=char\HP+8
								If char\hp>char\mhp
					char\hp=char\mhp
				EndIf

			End If
		Next
	EndIf
Next
End Function

Function CS_1_5(caster)
For cleric.clerical=Each clerical
	If cleric\Level=1 And cleric\spell=5
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		party.party=First party
		party\light=party\light+1
	EndIf
Next
End Function

Function CS_1_6(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=1 And cleric\spell=6
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-(cleric\cost[1])*char\lvl
				char\gems=char\gems-cleric\cost[2]
				heal=(Rnd(9)+1)*char\lvl
				DebugLog heal
			EndIf
			If char\ID=target
				char\hp=char\hp+heal
				If char\hp>char\mhp
					char\hp=char\mhp
				EndIf
				DebugLog char\name$+" just got "+heal+" HP back"
			End If
		Next
	EndIf
Next
End Function

Function CS_1_7(caster)
For cleric.clerical=Each clerical
	If cleric\Level=1 And cleric\spell=7
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
				effected=Rnd(1)
				If effected=1 And bmon\undead=1
					DebugLog "monster:"+bmon\ID+" got turned"
					updatemonsters(bmon\ID)
				EndIf
			
		Next
	EndIf
Next
End Function

; Level 2
Function CS_2_1(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=2 And cleric\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			If char\ID=target 
				char\hp=char\HP+15
			EndIf
							If char\hp>char\mhp
					char\hp=char\mhp
				EndIf

		Next
	EndIf
Next
End Function


Function CS_2_2(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=2 And cleric\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			If char\ID=target
				char\LVL=char\LVL+6
			EndIf
		Next
	EndIf
Next
End Function

Function CS_2_3(caster)
For cleric.clerical=Each clerical
	If cleric\Level=2 And cleric\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
	EndIf
Next
	; err this spell needs me to fix later, once I have figured out what locations go where other than day 93 of course :)
End Function

Function CS_2_4(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=2 And cleric\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
				If bmon\undead=0
					bmon\Hp=bmon\HP-(Rnd(14)+2)
				EndIf
			EndIf
		Next
	EndIf
Next
End Function

Function CS_2_5(caster)
For cleric.clerical=Each clerical
	If cleric\Level=2 And cleric\spell=5
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
	EndIf
Next
party.party=First party
party\protelm=1
End Function

Function CS_2_6(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=2 And cleric\spell=6
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
				effect=4+char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+effect
				effected=Rnd(bmon\mres)
				If effected<10
					DebugLog "monster:"+bmon\ID+" is silenced"
					bmon\silence=1
				EndIf
			EndIf
		Next
	EndIf
Next
End Function

Function CS_2_7(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=2 And cleric\spell=7
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+cleric\effect
				effected=Rnd(bmon\mres)
				If effected<10
					DebugLog "monster:"+bmon\ID+" is weakened"
				EndIf
			EndIf
		Next
	EndIf
Next
End Function

; Level 3
Function CS_3_1(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=3 And cleric\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+cleric\effect
				bmon\HP=bmon\Hp-25
			EndIf
		Next
	EndIf
Next
End Function

Function CS_3_2(caster)
For cleric.clerical=Each clerical
	If cleric\Level=3 And cleric\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
				char\food=char\food+8
			EndIf
		Next
		
	EndIf
Next
End Function
Function CS_3_3(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=3 And cleric\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			If char\ID=target
			char\Posion=0
			EndIf 
		Next
	EndIf
Next
End Function
Function CS_3_4(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=3 And cleric\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+cleric\effect
				effected=Rnd(bmon\mres)
				If effected<10
					DebugLog "monster:"+bmon\ID+" is imobalised"
				EndIf
			EndIf
		Next
	EndIf
Next
End Function
Function CS_3_5(caster)
For cleric.clerical=Each clerical
	If cleric\Level=3 And cleric\spell=5
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
				party.party=First party
				party\light=party\light+20
			EndIf
		Next
	EndIf
Next

End Function
Function CS_3_6(caster)
For cleric.clerical=Each clerical
	If cleric\Level=3 And cleric\spell=6
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
	EndIf
Next
; do walk on water stuff once the collision is setup to allow it :)
End Function


Function CS_4_1(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=4 And cleric\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+3
			bmon\hp=bmon\hp-(Rnd(54)+6)
			
			EndIf
		Next
	EndIf
Next
End Function

Function CS_4_2(caster)
For cleric.clerical=Each clerical
	If cleric\Level=4 And cleric\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			party.party= First party
;			party.air=1

		Next
	EndIf
Next
End Function

Function CS_4_3(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=4 And cleric\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			If char\ID=target
			char\disease=0
			EndIf 
		Next
	EndIf
Next
End Function

Function CS_4_4(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=4 And cleric\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			If char\ID=target
;			char\allign=...
			EndIf 
		Next
	EndIf
Next
End Function

Function CS_4_5(caster)
For cleric.clerical=Each clerical
	If cleric\Level=3 And cleric\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
;.....
		Next
	EndIf
Next
End Function

Function CS_4_6(caster)
For cleric.clerical=Each clerical
	If cleric\Level=4 And cleric\spell=6
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			party.party =First party
			party\dambonus=1+(char\lvl/2)

		Next
	EndIf
Next
End Function

; level 5 cleric spells
Function CS_5_1(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=5 And cleric\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target 
				bmon\airencased=1			
			EndIf
		Next
	EndIf
Next
End Function

Function CS_5_2(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=5 And cleric\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+10
			bmon\hp=bmon\hp-(Rnd(40)+4)
			
			EndIf
		Next
	EndIf
Next
End Function
Function CS_5_3(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=5 And cleric\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			If char\id=target
				For a=1 To mon_no
				attack(char\id,a,1)
				Next
				char\hp=0
				
			End If
		Next
	EndIf
Next
End Function

Function CS_5_4(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=5 And cleric\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+10
			DebugLog bmon\name$+" is paralysed"
			
			EndIf
		Next
	EndIf
Next
End Function

Function CS_5_5(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=5 And cleric\spell=5
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			If char\ID=target
			char\Posion=0
			char\asleep=0
			char\disease=0
			char\silence=0
			EndIf
		Next
	EndIf
Next
End Function


; level 6 cleric spells
Function CS_6_1(caster) 
For cleric.clerical=Each clerical
	If cleric\Level=6 And cleric\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
			party.party= First party
;			party.earth=1
	EndIf
Next
End Function

Function CS_6_2(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=6 And cleric\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			If char\ID=target	
			char\age=char\age-(Rnd(10))
			EndIf
		Next
		
	EndIf
Next
End Function

Function CS_6_3(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=6 And cleric\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			If char\ID=target
			char\stones=0
			End If
		Next
	EndIf
Next
End Function

Function CS_6_4(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=6 And cleric\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
			bmon\waterencased=1			
			EndIf
		Next
	EndIf
Next
End Function

Function CS_6_5(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=6 And cleric\spell=5
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			party.party= First party
;			party.water=1
		Next
	EndIf
Next
End Function


; level 7 cleric spells
Function CS_7_1(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=7 And cleric\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target 
			bmon\earthencased=1
		EndIf
		Next
	EndIf
Next
End Function

Function CS_7_2(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=7 And cleric\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
			bmon\hp=bmon\hp-(Rnd(300)+100)
			
			EndIf
		Next
	EndIf
Next
End Function

Function CS_7_3(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=7 And cleric\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
			char\hp=char\hp+(Rnd(90)+10)
			If char\hp>char\mhp
				char\hp=char\mhp
			End If
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+10
			bmon\hp=bmon\hp-(Rnd(90)+10)
			
			EndIf
		Next
	EndIf
Next
End Function

Function CS_7_4(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=7 And cleric\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
				char\age=char\age+1
			EndIf
			If char\ID=target
				char\dead=0
				char\age=char\age+1
			EndIf
		Next
		
	EndIf
Next
End Function


; level 8 cleric spells
Function CS_8_1(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=8 And cleric\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
			bmon\fireencased=1
			
			EndIf
		Next
	EndIf
Next
End Function

Function CS_8_2(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=8 And cleric\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		party.party=First party
;		party\firetrans=1
	EndIf
Next
End Function

Function CS_8_3(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=8 And cleric\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target Or bmon\ID=target+1
			bmon\hp=(bmon\hp/2)
			DebugLog bmon\name$
			EndIf
		Next
	EndIf
Next
End Function

Function CS_8_4(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=8 And cleric\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		; add town portal later.
	EndIf
Next
End Function


; level 9 cleric spells
Function CS_9_1(caster)
For cleric.clerical=Each clerical
	If cleric\Level=4 And cleric\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
				char\age=char\age+5
			EndIf
			char\hp=char\mhp
			char\sp=char\msp
			char\dead=0
			char\posion=0
;			....		
		Next
		
	EndIf
Next
End Function

Function CS_9_2(caster)
For cleric.clerical=Each clerical
	If cleric\Level=9 And cleric\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
			For bmon.battlemonst=Each battlemonst
			If bmon\undead=1
			updatemonsters(bmon\ID)			
			EndIf
		Next

	EndIf
Next
End Function

Function CS_9_3(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=9 And cleric\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
				char\age=char\age+1
			EndIf
			If char\ID=target
				char\erad=0
			EndIf
		Next
	EndIf
Next
End Function

Function CS_9_4(caster,target)
For cleric.clerical=Each clerical
	If cleric\Level=9 And cleric\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-cleric\cost[1]
				char\gems=char\gems-cleric\cost[2]
			EndIf
		Next
		; even though this spell is in the game I have NEVER got a cursed item while playing..
		; so this spell will be ignored for now...
	EndIf
Next
End Function


; Sorceror Spells
; Level 1
Function SS_1_1(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=1 And sorc\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
			CHAR\ASLEEP=0
		Next
		
	EndIf
Next
End Function


Function SS_1_2(castert)
For sorc.sorceror=Each sorceror
	If sorc\Level=1 And sorc\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				; insert detect magic stuff here
			EndIf
		Next
	EndIf
Next
End Function

Function SS_1_3(caster,target)
DebugLog "CASTER:"+caster
For sorc.sorceror=Each sorceror
	If sorc\Level=1 And sorc\spell=3
		For char.character=Each character
			If char\ID=caster
				DebugLog char\name
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				damage=(Rnd(5)+1)*char\lvl
				DebugLog "DAM:"+damage
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
				bmon\hp=bmon\hp-damage
			EndIf
		Next
	EndIf
Next
End Function

Function SS_1_4(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=1 And sorc\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
				bmon\hp=bmon\hp-(Rnd(6)+2)
				If bmon\hp<=0 
					updatemonsters(bmon\ID)
				End If

			EndIf
		Next
	EndIf
Next
End Function

Function SS_1_5(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=1 And sorc\spell=5
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
			party.party=First party
			party\light=party\light+1
		Next
	EndIf
Next
End Function

Function SS_1_6(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=1 And sorc\spell=6
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		;drawmap2da()
	EndIf
Next
End Function

Function SS_1_7(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=1 And sorc\spell=7
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+effect
				effected=Rnd(bmon\mres)
				If effected<10
					DebugLog "monster:"+bmon\ID+" is asleep"
				EndIf
			EndIf
		Next
	EndIf
Next
End Function





Function SS_2_1(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=2 And sorc\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		; insert eagle eye stuff here
	EndIf
Next
End Function

Function SS_2_2(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=2 And sorc\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
				bmon\hp=bmon\hp-(Rnd(12)+4)
				If bmon\hp<=0 
					updatemonsters(bmon\ID)
				End If
			EndIf
		Next
	EndIf
Next
End Function

Function SS_2_3(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=2 And sorc\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target 
				Color 0,255,255
				DrawImage txtbox,(GraphicsWidth()/2)-(Len(message$)*4)-180,GraphicsHeight()/2-30
				message$=bMon\ID+": "+bmon\name$+" HP:"+(bmon\hp)
				message2$="Undead: "+bmon\undead+" Archer: "+bmon\Archer+" AC:"+bmon\ac+" MRES:"+bmon\mres
				Text (GraphicsWidth()/2)-(Len(message$)*4),GraphicsHeight()/2,message$
				Text (GraphicsWidth()/2)-(Len(message2$)*4),(GraphicsHeight()/2)+16,message2$
				Color 255,255,255
				Flip
				FlushKeys
				Repeat
				a=GetKey()
				Until a<>0
			EndIf
		Next
	EndIf
Next
End Function

Function SS_2_4(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=2 And sorc\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		; insert jump code here
	EndIf
Next
End Function

Function SS_2_5(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=2 And sorc\spell=5
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		party.party=First party
		party\floating=1
	EndIf
Next
End Function

Function SS_2_6(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=2 And sorc\spell=6
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		; insert lloyd's beacon code here
	EndIf
Next
End Function

Function SS_2_7(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=2 And sorc\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		party.party=First party
		party\protmag=1

	EndIf
Next
End Function

Function SS_3_1(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=3 And sorc\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				damage=(Rnd(6)+2)*char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
				bmon\hp=bmon\hp-damage
				If bmon\hp<=0 
					updatemonsters(bmon\ID)
				End If

			EndIf
		Next
	EndIf
Next
End Function

Function SS_3_2(caster,x,y)
For sorc.sorceror=Each sorceror
	If sorc\Level=3 And sorc\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		; insert fly code here :)
	EndIf
Next
End Function


Function SS_3_3(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=3 And sorc\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
			party.party=First party
			party\invis=1
			
		Next
	EndIf
Next
End Function


Function SS_3_4(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=3 And sorc\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				damage=(Rnd(5)+1)*char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+sorc\effect
				bmon\hp=bmon\hp-damage
				If bmon\hp<=0 
					updatemonsters(bmon\ID)
				End If
			EndIf
		Next
	EndIf
Next
End Function


Function SS_3_5(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=3 And sorc\spell=5
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+effect
					DebugLog "monster:"+bmon\ID+" is stuck"
			EndIf
		Next
	EndIf
Next
End Function


Function SS_3_6(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=3 And sorc\spell=6
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		; insert wizards eye code here
	EndIf
Next
End Function

; level 4
Function SS_4_1(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=4 And sorc\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				damage=6*char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
				bmon\hp=bmon\hp-damage
			EndIf
		Next
	EndIf
Next
End Function
Function SS_4_2(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=4 And sorc\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+5
					DebugLog "monster:"+bmon\ID+" is dumb"
					
			EndIf
		Next
	EndIf
Next
End Function
Function SS_4_3(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=4 And sorc\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				damage=(Rnd(5)+1)*char\lvl

			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+6
					bmon\hp=bmon\hp-damage

			EndIf
		Next
	EndIf
Next
End Function
Function SS_4_4(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=4 And sorc\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		party.party=First party
;		party.guard=1
	EndIf
Next
End Function
Function SS_4_5(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=4 And sorc\spell=5
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
			party.party=First party
;		party.shield=1
	EndIf
Next
End Function
Function SS_4_6(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=4 And sorc\spell=6
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		;end ; this will have to end combat somehow...
	EndIf
Next
End Function


; level 5
Function SS_5_1(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=5 And sorc\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
			bmon\hp=bmon\hp-100
			EndIf
		Next
	EndIf
Next
End Function

Function SS_5_2(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=5 And sorc\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+3
				If bmon\undead=0
					bmon\HP=0
				End If 
			EndIf
		Next
	EndIf
Next
End Function

Function SS_5_3(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=5 And sorc\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				damage=(Rnd(7)+1)*char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+9
				bmon\hp=bmon\hp-damage
			EndIf
		Next
	EndIf
Next
End Function

Function SS_5_4(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=5 And sorc\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
			party.party=First party
			;party.shelter=1
		Next
	EndIf
Next
End Function

Function SS_5_5(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=5 And sorc\spell=5
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		; add teleport code....
	EndIf
Next
End Function


; level 6
Function SS_6_1(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=6 And sorc\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+3
				kill=Rnd(2)
				If kill=2
					bmon\hp=0
				Else
					bmon\hp=bmon\hp-50				
				EndIf
			EndIf 
			Next
	EndIf
Next
End Function

Function SS_6_2(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=6 And sorc\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
			party.party=First party
			;party\entrap=1
			
		Next
	EndIf
Next
End Function

Function SS_6_3(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=6 And sorc\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				level=char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=>target And bmon\ID<=target+3
				bmon\hp=bmon\HP-(level)*10
			EndIf
		Next
	EndIf
Next
End Function

Function SS_6_4(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=6 And sorc\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		; insert recharge item code here.......
	EndIf
Next
End Function

Function SS_6_5(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=6 And sorc\spell=5
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				level=char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
							bmon\hp=bmon\HP-(level)*20

			EndIf
		Next
	EndIf
Next
End Function


; level 7
Function SS_7_1(caster)  ; my favourate spell other then Star burst :) eat dancing sword mofo's
For sorc.sorceror=Each sorceror
	If sorc\Level=7 And sorc\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				level=char\lvl
								message$=char\name$+" Casts:"+sorc\name$
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
		If bmon\ID=>1 And bmon\ID<=10
		damage=((Rnd(11)+1)*level)
			bmon\hp=bmon\hp-damage ; shit loads of damage..... 
		
		message2$="and hits "+bmon\Name
		message3$="For "+damage+" damage."
		RenderWorld
UpdateWorld
showtext
				DrawImage txtbox,(GraphicsWidth()/2)-(Len(message$)*4)-20,GraphicsHeight()/2-10
Text (GraphicsWidth()/2)-(Len(message$)*4),GraphicsHeight()/2,message$
Text (GraphicsWidth()/2)-(Len(message2$)*4),(GraphicsHeight()/2)+16,message2$
Text (GraphicsWidth()/2)-(Len(message3$)*4),(GraphicsHeight()/2)+32,message3$
Flip
Delay dlay*6
EndIf
		Next
	EndIf
Next
End Function

Function SS_7_2(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=7 And sorc\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		; insert duplicate code to character, isn't hard.
	EndIf
Next
End Function

Function SS_7_3(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=7 And sorc\spell=3
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		; insert code for etherilise here :P
	EndIf
Next
End Function

Function SS_7_4(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=7 And sorc\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
		Next
		; hmm, fun part this cast any other combat spell but has an effect on all 10 monsters.
	EndIf
Next
End Function


; level 8
Function SS_8_1(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=8 And sorc\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
				damage=(Rnd(20)+20)*char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
				bmon\hp=bmon\hp-damage
			EndIf
		Next
	EndIf
Next
End Function

Function SS_8_2(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=8 And sorc\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				level=char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
		If bmon\ID=>1 And bmon\ID<=10
			bmon\hp=bmon\hp-((Rnd(12)+4)*level) ; shit loads of damage..... 
		EndIf
		Next
	EndIf
Next
End Function

Function SS_8_3(caster) ; meteor Shower similar to starburst but not as cool.
For sorc.sorceror=Each sorceror
	If sorc\Level=8 And sorc\spell=3
		For char.character=Each character
			If char\ID=caster
			cost=sorc\cost[1]+(amount+no_mon)
				char\SP=char\SP-cost
				char\gems=char\gems-sorc\cost[2]
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			bmon\HP=bmon\HP-(Rnd(45)+5)
		Next
	EndIf
Next
End Function

Function SS_8_4(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=8 And sorc\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
			party.party=First party
;			party\powershld=1
		Next
	EndIf
Next
End Function


; level 9
Function SS_9_1(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=9 And sorc\spell=1
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
				damage=((Rnd(1)+1)*500)
				message$=char\name$+" Casts:"+sorc\name$
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
			If bmon\ID=target
				bmon\hp=bmon\hp-damage
				message2$="and hits "+bmon\Name
				message3$="For "+damage+" damage."

				DrawImage txtbox,(GraphicsWidth()/2)-(Len(message$)*4)-20,GraphicsHeight()/2-10
Text (GraphicsWidth()/2)-(Len(message$)*4),GraphicsHeight()/2,message$
Text (GraphicsWidth()/2)-(Len(message2$)*4),(GraphicsHeight()/2)+16,message2$
Text (GraphicsWidth()/2)-(Len(message3$)*4),(GraphicsHeight()/2)+32,message3$
Flip
Delay dlay*8

			EndIf
		Next
	EndIf
Next
End Function

Function SS_9_2(caster,target)
For sorc.sorceror=Each sorceror
	If sorc\Level=9 And sorc\spell=2
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]*char\lvl
				char\gems=char\gems-sorc\cost[2]
				level=char\lvl
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
		If bmon\ID=>1 And bmon\ID<=10
			bmon\hp=bmon\hp-((Rnd(19)+1)*level) ; shit loads of damage..... 
		EndIf
		Next
	EndIf
Next
End Function

Function SS_9_3(caster) ; meteor Shower similar to starburst but not as cool.
For sorc.sorceror=Each sorceror
	If sorc\Level=9 And sorc\spell=3
		For char.character=Each character
			If char\ID=caster
			cost=sorc\cost[1]+(amount+no_mon)
				char\SP=char\SP-cost
				char\gems=char\gems-sorc\cost[2]
				message$=char\name$+" Casts:"+sorc\name$
			EndIf
		Next
		For bmon.battlemonst=Each battlemonst
		damage=(Rnd(180)+20)
			bmon\HP=bmon\HP-damage
							message2$="and hits "+bmon\Name
				message3$="For "+damage+" damage."
RenderWorld
UpdateWorld
showtext
				DrawImage txtbox,(GraphicsWidth()/2)-(Len(message$)*4)-20,GraphicsHeight()/2-10
Text (GraphicsWidth()/2)-(Len(message$)*4),GraphicsHeight()/2,message$
Text (GraphicsWidth()/2)-(Len(message2$)*4),(GraphicsHeight()/2)+16,message2$
Text (GraphicsWidth()/2)-(Len(message3$)*4),(GraphicsHeight()/2)+32,message3$
Flip
Delay dlay*6
		Next
	EndIf
Next
End Function

Function SS_9_4(caster)
For sorc.sorceror=Each sorceror
	If sorc\Level=9 And sorc\spell=4
		For char.character=Each character
			If char\ID=caster
				char\SP=char\SP-sorc\cost[1]
				char\gems=char\gems-sorc\cost[2]
				effect=sorc\effect+char\lvl
			EndIf
			; insert enchant item code here
		Next
	EndIf
Next
End Function