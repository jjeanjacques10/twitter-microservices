package com.twitter.gateway.client

import com.twitter.gateway.client.datacontract.FollowersDataContract
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@FeignClient(value = "user-followers", url = "\${client.user-followers.url}")
interface FollowersClient {
    @RequestMapping(method = [RequestMethod.GET], value = ["/user/follow/{userId}/followers"])
    fun getFollowers(@PathVariable userId: String?): FollowersDataContract?
}