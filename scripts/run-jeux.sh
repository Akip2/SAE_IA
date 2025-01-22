#!/bin/bash

# command
JAR_DIR='../dist'
JAR='Games.jar' 
CMD="java -jar ${JAR_DIR}/${JAR}"

# defaults
P1='random'
P2='random'
GAME='tictactoe'
DEPTH='-1'
RUNS='15'
VERBOSE="no"

# Read cli arguments 
while [[ $# -gt 0 ]]; do
    case $1 in
        -p1|--player1)
            p1="$2" && shift && shift
            [[ "$p1" == 'random' ||\
		           "$p1" == 'minmax' ||\
		           "$p1" == 'alphabeta' ]] && P1=${p1}
            ;;
        -p2|--player2)
            p2="$2" && shift && shift
            [[ "$p2" == 'random' ||\
		           "$p2" == 'minmax' ||\
		           "$p2" == 'alphabeta' ]] && P2=${p2}
            ;;
        -g|--game)
            g="$2" && shift && shift
            [[ "$g" == 'tictactoe' ||\
		           "$g" == 'connect4' ||\
		           "$g" == 'gomoku' ||\
		           "$g" == 'mnk' ]] && GAME=${g}
            ;;
        -d|--depth)
            DEPTH="$2" && shift && shift
            ;;
        -n|--nb_runs)
            RUNS="$2" && shift && shift
            ;;
        -h|--help)
            HELP=yes && shift
            ;;
        -v|--verbose)
            VERBOSE=yes && shift
            ;;
        *)
            POSITIONAL_ARGS+=("$1") # save positional arg
            shift # past argument
            ;;
        -*|--*)
            echo "Unknown option $1. Try `basename $0` -h"
            exit 2
            ;;
    esac
done

# usage message
if [[ $HELP == "yes" ]]; then
    echo "
Batch run of game code 
Usage: `basename $0` [-h|--help] [-g|--game tictactoe|connect4|gomoku|mnk] [-p1|--player1 random|minmax|alphabeta] [-p2|--player2 random|minmax|alphabeta] [-d|--depth int] [-v|verbose] 
    -h    Prints this help
    -g    The game to play (default tictactoe)
    -p1   Player 1 (default random)
    -p2   Player 2 (default random)
    -d    Game tree max depth (default no limit)
    -n    Number of runs (default 15)
    -v    Verbose (Default no)
"
    exit 0
fi

# Go go go 
ARGS="-p1 ${P1} -p2 ${P2} -game ${GAME} -d ${DEPTH}"
echo "# Running : $CMD $ARGS"
echo "# Use -h for help."

for ((i = 0 ; i < $RUNS ; i++ )); do
    if [[ $VERBOSE == "no" ]]; then 
        $CMD $ARGS >/dev/null
        STATUS=$?
    else
        $CMD $ARGS 
        STATUS=$?
    fi

    if [[ $STATUS -eq 101 ]]; then
        echo -e "${i}\tP1"
    elif [[  $STATUS -eq 102 ]]; then
        echo -e "${i}\tP2"
    elif [[  $STATUS -eq 100 ]]; then
        echo -e "${i}\tNUL"
    else
        echo -e "${i}\tERROR"
    fi
done



