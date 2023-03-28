	db MACHAMP ; 68

	db  90,  130,  80,  55,  65,  85
  ;  hp  atk  def  spd sat sdf

	db FIGHTING, FIGHTING ; type
	db 45 ; catch rate
	db 227 ; base exp
	db NO_ITEM, NO_ITEM
	db GENDER_F25 ; gender ratio
	db 100 ; unknown 1
	db 20 ; step cycles to hatch
	db 5 ; unknown 2
	INCBIN "gfx/pokemon/machamp/front.dimensions
	dw NULL, NULL ; unused (beta front/back pics)
	db GROWTH_MEDIUM_SLOW ; growth rate
	dn EGG_HUMANLIKE, EGG_HUMANLIKE ; egg groups

	; tm/hm learnset
	tmhm THROAT_CHOP, KNOCK_OFF, THIEF, PAYBACK, FLING, DUAL_CHOP, THUNDER_PUNCH, FOCUS_PUNCH, FOCUS_BLAST, SUPERPOWER, BRICK_BREAK, LOW_SWEEP, POWER_UP_PUNCH, ROCK_SMASH, LOW_KICK, BULK_UP, FIRE_BLAST, FLAMETHROWER, FIRE_PUNCH, SUNNY_DAY, EARTHQUAKE, STOMPING_TANTRUM, BULLDOZE, ICE_PUNCH, GIGA_IMPACT, HYPER_BEAM, STRENGTH, FACADE, HIDDEN_POWER, ROUND, SNORE, FRUSTRATION, RETURN, ATTRACT, CONFIDE, DOUBLE_TEAM, HELPING_HAND, PROTECT, SLEEP_TALK, SUBSTITUTE, SWAGGER, WORK_UP, POISON_JAB, TOXIC, LIGHT_SCREEN, REST, ROLE_PLAY, STONE_EDGE, ROCK_SLIDE, ROCK_TOMB, SMACK_DOWN, RAIN_DANCE, ENDURE, MEGA_KICK, BODY_SLAM, HEAVY_SLAM, DARKEST_LARIAT, MEGA_PUNCH, HIGH_HORSEPOWER, FOCUS_ENERGY, ASSURANCE, SCARY_FACE, REVERSAL, COACHING, REVENGE, METRONOME, DIG, RETALIATE, ROCK_BLAST, CLOSE_COMBAT, ENCORE, CROSS_POISON
	; end