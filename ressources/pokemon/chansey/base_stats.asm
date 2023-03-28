	db CHANSEY ; 113

	db  250,  5,  5,  50,  35,  105
  ;  hp  atk  def  spd sat sdf

	db NORMAL, NORMAL ; type
	db 30 ; catch rate
	db 395 ; base exp
	db NO_ITEM, NO_ITEM
	db GENDER_F100 ; gender ratio
	db 100 ; unknown 1
	db 40 ; step cycles to hatch
	db 5 ; unknown 2
	INCBIN "gfx/pokemon/chansey/front.dimensions
	dw NULL, NULL ; unused (beta front/back pics)
	db GROWTH_FAST ; growth rate
	dn EGG_FAIRY, EGG_FAIRY ; egg groups

	; tm/hm learnset
	tmhm FLING, SNATCH, THUNDER, THUNDERBOLT, WILD_CHARGE, THUNDER_PUNCH, SHOCK_WAVE, CHARGE_BEAM, THUNDER_WAVE, DAZZLING_GLEAM, FOCUS_PUNCH, BRICK_BREAK, DRAIN_PUNCH, POWER_UP_PUNCH, ROCK_SMASH, FIRE_BLAST, FLAMETHROWER, FIRE_PUNCH, SUNNY_DAY, SHADOW_BALL, SOLAR_BEAM, GRASS_KNOT, EARTHQUAKE, STOMPING_TANTRUM, BULLDOZE, BLIZZARD, ICE_BEAM, ICE_PUNCH, ICY_WIND, HAIL, GIGA_IMPACT, HYPER_BEAM, LAST_RESORT, HYPER_VOICE, UPROAR, STRENGTH, FACADE, COVET, HIDDEN_POWER, ROUND, SNORE, ECHOED_VOICE, ENDEAVOR, FRUSTRATION, RETURN, ATTRACT, CONFIDE, DOUBLE_TEAM, HEAL_BELL, HELPING_HAND, LASER_FOCUS, PROTECT, PSYCH_UP, RECYCLE, SAFEGUARD, SLEEP_TALK, SUBSTITUTE, SWAGGER, WORK_UP, TOXIC, DREAM_EATER, PSYCHIC, ZEN_HEADBUTT, ALLY_SWITCH, CALM_MIND, GRAVITY, LIGHT_SCREEN, REFLECT, REST, SKILL_SWAP, TELEKINESIS, ROCK_SLIDE, ROCK_TOMB, SANDSTORM, STEALTH_ROCK, IRON_TAIL, WATER_PULSE, RAIN_DANCE, ENDURE, MEGA_KICK, BODY_SLAM, MEGA_PUNCH, METRONOME, RETALIATE, STORED_POWER, CHARM, TRI_ATTACK
	; end