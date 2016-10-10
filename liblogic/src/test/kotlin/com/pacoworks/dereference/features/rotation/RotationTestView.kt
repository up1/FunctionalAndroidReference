/*
 * Copyright (c) pakoito 2016
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pacoworks.dereference.features.rotation

import com.jakewharton.rxrelay.PublishRelay
import com.jakewharton.rxrelay.SerializedRelay
import com.pacoworks.dereference.bindAsTest
import com.pacoworks.dereference.mockView
import rx.Observable
import rx.functions.Action2
import java.util.concurrent.atomic.AtomicLong

class MockRotationViewOutput : RotationViewOutput {
    val userInputPRelay = PublishRelay.create<String>()

    override fun enterUser(): Observable<String> =
            userInputPRelay

}

class MockRotationViewInput : RotationViewInput {
    val loadingCount = AtomicLong()
    val errorCount = AtomicLong()
    val waitingCount = AtomicLong()
    val successCount = AtomicLong()

    override fun setLoading(user: String) =
            mockView<String>(loadingCount).invoke(user)

    override fun showError(reason: String) =
            mockView<String>(errorCount).invoke(reason)

    override fun setWaiting(seconds: Int) =
            mockView<Int>(waitingCount).invoke(seconds)

    override fun showRepos(value: String) =
            mockView<String>(successCount).invoke(value)

    override fun <T> createBinder(): Action2<SerializedRelay<T, T>, (T) -> Unit> =
            bindAsTest()
}