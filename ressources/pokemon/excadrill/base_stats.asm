	db EXCADRILL ; 530

	db  110,  135,  60,  88,  50,  65
  ;  hp  atk  def  spd sat sdf

	db GROUND, STEEL ; type
	db 60 ; catch rate
	db 178 ; base exp
	db NO_ITEM, NO_ITEM
	db GENDER_F50 ; gender ratio
	db 100 ; unknown 1
	db 20 ; step cycles to hatch
	db 5 ; unknown 2
	INCBIN "gfx/pokemon/excadrill/front.dimensions
	dw NULL, NULL ; unused (beta front/back pics)
	db GROWTH_MEDIUM_FAST ; growth rate
	dn EGG_FIELD, EGG_FIELD ; egg groups

	; tm/hm learnset
	tmhm X_SCISSOR, BRUTAL_SWING, FLING, MAGNET_RISE, FOCUS_BLAST, BRICK_BREAK, ROCK_SMASH, AERIAL_ACE, SHADOW_CLAW, EARTHQUAKE, EARTH_POWER, DRILL_RUN, STOMPING_TANTRUM, BULLDOZE, GIGA_IMPACT, HYPER_BEAM, STRENGTH, FACADE, HIDDEN_POWER, ROUND, CUT, SNORE, FRUSTRATION, RETURN, ATTRACT, CONFIDE, DOUBLE_TEAM, PROTECT, SLEEP_TALK, SUBSTITUTE, SWAGGER, SWORDS_DANCE, SLUDGE_BOMB, POISON_JAB, TOXIC, REST, ROCK_SLIDE, ROCK_TOMB, SANDSTORM, STEALTH_ROCK, IRON_HEAD, SMART_STRIKE, IRON_DEFENSE, ENDURE, STEEL_BEAM, HIGH_HORSEPOWER, MUD_SHOT, SCORCHING_SANDS, DIG, SAND_TOMB, ROCK_BLAST
	; end