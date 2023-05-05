package 春招实习笔试.拼多多.笔试0312;

/**
 * @author mxy
 * @create 2023-03-13 16:50
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://mp.weixin.qq.com/s?__biz=MzU2Mzg0OTQxOQ==&mid=2247484989&idx=1&sn=3256158958b7b9dc4135805d23cda3f2&chksm=fc52b799cb253e8fff762fb2814acbddb6602e00d4b56a920f06a3b7cd4fc68ef582d6de7ad3&cur_album_id=2828382526552391682&scene=189#wechat_redirect
 * 团建规划
 * 又到了团建的时间,多多君负责安排这次的团建活动。
 * 多多君准备了三个活动（分别编号A、B和C)，每个活动分别有人数上限以及每个人参加的费用。
 * 参加团建的有N个人(分别编号1~N）,每个人先投票选择若干个意向的活动,最终每个人只能参加其中一个。
 * 多多君收集完投票结果后,发现如何安排成为了大难题:如何在满足所有人的意向的情况下,使得活动的总费用最少。
 * 于是多多君找到了擅长编程的你,希望你能帮助找到个合理的团建计划。
 * 输入描述
 * 第一行,一个整数N,代表准备参加活动的人数。(1<=N<=100 )
 * 接下来N行,每行一个由"ABC"组成的字符串,其中第i行表示第i个人投票了哪几个活动。
 * (输入保证字符串非空,且由大写的"ABC"字符组成)
 * 最后3行,每行两个整数,分别表示三个活动的人数上限以及每个人参加的费用。
 * (人数上限以及参与活动的费用均为不超过100的正整数)
 * 输出描述
 * 输出共2行
 * 如果能满足所有人的要求,第一行输出"YES”,第二行输出最少的总费用。
 * 如果不能满足所有人的要求,第一行输出"NO，第二行输出最多能满足多少人。
 * 示例
 * 输入
 * 5
 * A
 * B
 * C
 * AB
 * ABC
 * 2 1
 * 2 2
 * 2 3
 * 输出
 * YES
 * 9
 * 示例2
 * 输入
 * 5
 * A
 * B
 * C
 * AB
 * AB
 * 1 1
 * 2 2
 * 3 3
 * 输出
 * NO
 * 4
 */
public class GroupPlan {

    public static void main(String[] args) {
        //int i = 1<<30;
        //System.out.println(i);
        //System.out.println(Integer.MAX_VALUE);
    }

/* 最小费用最大流模板题：

from collections import deque

INF = float('inf')

def add_edge(u, v, c, w):
    edges.append([u, v, c, w])
    edges.append([v, u, 0, -w])
    graph[u].append(len(edges) - 2)
    graph[v].append(len(edges) - 1)

def spfa(source, sink):
    dist = [INF] * (n + 1)
    visited = [False] * (n + 1)
    in_queue = [False] * (n + 1)
    pre_edge = [-1] * (n + 1)
    dist[source] = 0
    q = deque()
    q.append(source)
    in_queue[source] = True
    while q:
        u = q.popleft()
        in_queue[u] = False
        visited[u] = True
        for i in graph[u]:
            v, c, w = edges[i][1], edges[i][2], edges[i][3]
            if c > 0 and dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
                pre_edge[v] = i
                if not in_queue[v]:
                    q.append(v)
                    in_queue[v] = True
    return visited[sink], dist, pre_edge

def mcmf(source, sink):
    max_flow, min_cost = 0, 0
    while True:
        res, dist, pre_edge = spfa(source, sink)
        if not res:
            break
        flow = INF
        u = sink
        while u != source:
            i = pre_edge[u]
            flow = min(flow, edges[i][2])
            u = edges[i][0]
        max_flow += flow
        min_cost += flow * dist[sink]
        u = sink
        while u != source:
            i = pre_edge[u]
            edges[i][2] -= flow
            edges[i ^ 1][2] += flow
            u = edges[i][0]
    return max_flow, min_cost


n = int(input())
people = []
for _ in range(n):
    people.append(input())
nums = {}
costs = {}
for i in range(3):
    num, c = map(int, input().split(" "))
    nums[i + n + 1] = num
    costs[i + n + 1] = c

graph = [[] for _ in range(n + 5)]
edges = []

peo_acv_map = {'A':n + 1, 'B':n+2, 'C':n+3}

for i in range(len(people)):
    add_edge(0, i + 1, 1, 0) #源点到达人的边
    #当前这个人感兴趣的活动
    activ = []
    for c in people[i]:
        activ.append(peo_acv_map[c])
    #人到达活动的边
    for act in activ:
        add_edge(i+1, act, 1, costs[act])

#活动到汇点
add_edge(n + 1, n + 4, nums[n + 1], 0)
add_edge(n + 2, n + 4, nums[n + 2], 0)
add_edge(n + 3, n + 4, nums[n + 3], 0)

n += 5
# 从源点到汇点的最大流和最小费用
source = 0
sink = n - 1

max_flow, min_cost = mcmf(source, sink)
if max_flow == n - 5:
    print("YES")
    print(min_cost)
else:
    print("NO")
    print(max_flow)

*/
//https://blog.csdn.net/King8611/article/details/83268224
    static int v,m,s,t,f;                            //点的个数，边的个数，源点，汇点，流量
    static final int INF = Integer.MAX_VALUE;        //无穷大0
    static ArrayList<Edge> edges[];                    //图的邻接表表示

    public static void add_edge(){
        /*
        def add_edge(u, v, c, w):
            edges.append([u, v, c, w])
            edges.append([v, u, 0, -w])
            graph[u].append(len(edges) - 2)
            graph[v].append(len(edges) - 1)
         */
    }

}

class Edge{
    int to,cap,cost,rev; //终点、容量、费用、反向边
    public Edge(int to,int cap,int cost,int rev){
        this.to = to;
        this.cap = cap;
        this.cost = cost;
        this.rev = rev;
    }

}
