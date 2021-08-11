import sys
from typing import Dict, List


def solution(teams: List[str], games: List[List[str]]) -> None:
    team_score = dict.fromkeys(teams, 0)
    team_probability = dict.fromkeys(teams, 0.0)
    dfs(0, team_score, 1, team_probability)
    for prob in team_probability.values():
        print(prob)


def dfs(idx: int, team_score: Dict[str, int],
        probability: float, team_probability: Dict[str, int]) -> None:
    def calculate_prob() -> None:
        team1 = sorted_team_score[0][0]
        team1_scroe = sorted_team_score[0][1]
        team2 = sorted_team_score[1][0]
        team2_scroe = sorted_team_score[1][1]
        team3 = sorted_team_score[2][0]
        team3_scroe = sorted_team_score[2][1]
        team4 = sorted_team_score[3][0]
        team4_scroe = sorted_team_score[3][1]

        # 4개 팀 모두 동률
        if team1_scroe == team2_scroe == team3_scroe == team4_scroe:
            team_probability[team1] += probability * (1/2)
            team_probability[team2] += probability * (1/2)
            team_probability[team3] += probability * (1/2)
            team_probability[team4] += probability * (1/2)
        # 상위 3개팀 동률
        elif team1_scroe == team2_scroe == team3_scroe:
            team_probability[team1] += probability * (2/3)
            team_probability[team2] += probability * (2/3)
            team_probability[team3] += probability * (2/3)
        # 하위 3개팀 동률
        elif team2_scroe == team3_scroe == team4_scroe:
            team_probability[team1] += probability
            team_probability[team2] += probability * (1/3)
            team_probability[team3] += probability * (1/3)
            team_probability[team4] += probability * (1/3)
        # 2,3등 동률
        elif team2_scroe == team3_scroe:
            team_probability[team1] += probability
            team_probability[team2] += probability * (1/2)
            team_probability[team3] += probability * (1/2)
        else:
            team_probability[team1] += probability
            team_probability[team2] += probability

    if probability == 0.0:
        return

    if idx == 6:
        sorted_team_score = sorted(
            list(team_score.items()), key=lambda x: x[1], reverse=True)
        calculate_prob()
    else:
        team_a = games[idx][0]
        team_b = games[idx][1]
        win_prob = games[idx][2]
        draw_prob = games[idx][3]
        lose_prob = games[idx][4]

        # a 승
        team_score[team_a] += 3
        dfs(idx + 1, team_score, probability * float(win_prob), team_probability)
        team_score[team_a] -= 3

        # 무승부
        team_score[team_a] += 1
        team_score[team_b] += 1
        dfs(idx + 1, team_score, probability * float(draw_prob), team_probability)
        team_score[team_a] -= 1
        team_score[team_b] -= 1

        # b 승
        team_score[team_b] += 3
        dfs(idx + 1, team_score, probability * float(lose_prob), team_probability)
        team_score[team_b] -= 3


input = sys.stdin.readline
teams = list(input().split())
games = [list(input().split()) for _ in range(6)]
solution(teams, games)
