Graphics 800,600
SetBuffer BackBuffer()
SeedRnd MilliSecs()
Global randnum = 10
Global xpos#, ypos#, prevxpos#, prevypos#
While Not KeyHit(1)
Color 255,255,255
Cls
ypos# = 0
xpos# = Rand(360,440)
randnum = randnum - 1
randnum = 10
While ypos# <= 800
xpos# = xpos# + Rnd(-5,5)
Color 0, Rand(100,150), 200
Rect xpos, ypos, 3, 3, 1
Color 0, Rand(150,200), 255
Rect xpos+1, ypos+1, 1, 1, 1
prevypos# = ypos
prevxpos# = xpos
ypos# = ypos# + 3
Wend
Flip
Wend