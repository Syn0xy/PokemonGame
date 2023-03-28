	db YAMASK-GALAR

	db  38,  55,  85,  30,  30,  65
  ;  hp  atk  def  spd sat sdf

	db GROUND, GHOST
	db 190 ; catch rate
	db 61 ; base exp
	db NO_ITEM, NO_ITEM
	db GENDER_F50 ; gender ratio
	db 100 ; unknown 1
	db 25 ; step cycles to hatch
	db 5 ; unknown 2
	INCBIN "gfx/pokemon/yamask/front.dimensions
	dw NULL, NULL ; unused (beta front/back pics)
	db GROWTH_MEDIUM_FAST ; growth rate
	dn EGG_MINERAL, EGG_AMORPHOUS ; egg groups

	; tm/hm learnset
	tmhm INFESTATION, DARK_PULSE, KNOCK_OFF, THIEF, PAYBACK, EMBARGO, SNATCH, SHOCK_WAVE, WILL_O_WISP, SHADOW_BALL, SPITE, ENERGY_BALL, FACADE, HIDDEN_POWER, ROUND, SNORE, FRUSTRATION, RETURN, AFTER_YOU, ATTRACT, BLOCK, CONFIDE, DOUBLE_TEAM, PAIN_SPLIT, PROTECT, PSYCH_UP, SAFEGUARD, SLEEP_TALK, SUBSTITUTE, SWAGGER, TOXIC, DREAM_EATER, PSYCHIC, ZEN_HEADBUTT, ALLY_SWITCH, CALM_MIND, MAGIC_COAT, REST, ROLE_PLAY, SKILL_SWAP, TELEKINESIS, TRICK, TRICK_ROOM, WONDER_ROOM, IRON_DEFENSE, RAIN_DANCE, ENDURE, NASTY_PLOT, FAKE_TEARS, IMPRISON, HEX, POLTERGEIST, TOXIC_SPIKES
	; end